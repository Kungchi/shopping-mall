package com.example.shopping.mall.service;

import com.example.shopping.mall.dto.productDto;
import com.example.shopping.mall.entity.ProductCategoryEntity;
import com.example.shopping.mall.entity.categoryEntity;
import com.example.shopping.mall.entity.productEntity;
import com.example.shopping.mall.entity.userEntity;
import com.example.shopping.mall.repository.ProductCategoryRepository;
import com.example.shopping.mall.repository.categoryRepository;
import com.example.shopping.mall.repository.productRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class productService {
    @Autowired
    productRepository productrepository;
    @Autowired
    ProductCategoryRepository productCategoryRepository;
    @Autowired
    categoryRepository categoryRepository;

    public List<productDto> index(Long id) { // 전 상품을 데이터베이스에서 조회
        List<productDto> dtos = new ArrayList<>();

        if(id == 0)
            dtos = productrepository.findAll()
                    .stream().map(entity -> entity.toDto()).collect(Collectors.toList());
        else {
            List<ProductCategoryEntity> entityList = productCategoryRepository.findByCategoryId(id);
            dtos = productrepository.findByIdIn(entityList.stream().map(entity -> entity.getId()).collect(Collectors.toList()))
                    .stream().map(entity -> entity.toDto()).collect(Collectors.toList());
        }
        return dtos;
    }

    public productDto show(Long id) { // 특정 상품의 자세한 정보를 데이터베이스에서 조회
        Optional<productEntity> target = productrepository.findById(id);
        return target.get().toDto();
    }

    public List<productDto> indexById(List<Long> productIds) { // 상품들을 데이터베이스에서 조회
        List<productEntity> entities = productrepository.findByIdIn(productIds);

        Map<Long, productDto> productMap = entities.stream().collect(Collectors.toMap(
                productEntity::getId,
                productEntity::toDto
        ));

        List<productDto> dtos = productIds.stream().map(id -> productMap.get(id)).collect(Collectors.toList());

        return dtos;
    }


    public String Img(MultipartFile file) throws IOException { // 이미지를 서버에 저장하는 메소드
        // 파일 저장 경로
        String uploadDir = "C:/Users/a0102/Desktop/img/"; // 이미지 주소 저장경로
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        // 파일 이름 생성
        String extension = file.getOriginalFilename() != null ? file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')) : "";
        String fileName = System.currentTimeMillis() + "_" + UUID.randomUUID().toString().replaceAll("[^a-zA-Z0-9]", "") + extension;


        // 파일 저장
        file.transferTo(new File(uploadDir + fileName));
        return fileName;
    }

    public void save(productDto dto, userEntity user, String category) { // 상품을 데이터베이스에 등록하고 카테고리에 등록하는 메소드

        productEntity productEntity = productrepository.save(dto.toEntity(user));
        categoryEntity categoryEntity = categoryRepository.findByName(category);

        ProductCategoryEntity pre = new ProductCategoryEntity(null ,productEntity, categoryEntity);
        productCategoryRepository.save(pre);
    }

    public List<productDto> findByUser(Long id) {
        List<productEntity> entities = productrepository.findByUserId(id);

        return entities.stream().map(productEntity::toDto).collect(Collectors.toList());

    }

    public String processContentImages(String content) throws IOException { // 상품을 등록할때 content에 이미지를 서버에 저장하는 메소드
        Document doc = Jsoup.parse(content);
        Elements imgTags = doc.select("img[src^=data:image]");  // Find all <img> tags with base64 data

        if (imgTags.isEmpty()) {
            return content;  // Return original content if there are no images
        }

        for (Element img : imgTags) {
            String base64Data = img.attr("src");
            String savedImageName = saveBase64Image(base64Data);

            // Replace the img's src attribute with the new path
            img.attr("src", "/"+ savedImageName);
        }

        return doc.toString();
    }

    public String saveBase64Image(String base64String) throws IOException { // base64로 되어있는 이미지를 해독하여 서버에 저장하는 메소드
        String[] parts = base64String.split(",");
        String imageString = parts[1];
        byte[] data = Base64.getDecoder().decode(imageString);

        String extension;
        switch (parts[0]) {  // Check the image type
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default:
                throw new IOException("Unsupported image type");
        }

        String fileName = System.currentTimeMillis() + "_" + UUID.randomUUID().toString().replaceAll("[^a-zA-Z0-9]", "") + extension;
        String imagePath = "C:/Users/a0102/Desktop/img/" + fileName; // 이미지 주소 저장경로

        try (OutputStream stream = new FileOutputStream(imagePath)) {
            stream.write(data);
        }

        return fileName;
    }


    public void active(Long id) { // 상품의 활성화, 비활성화 메소드
        productEntity entity = productrepository.findById(id).get();

        switch (entity.getStatus()) {
            case "Active":
                entity.setStatus("Disable");
                productrepository.save(entity);
                break;


            case "Disable":
                entity.setStatus("Active");
                productrepository.save(entity);
                break;

        }

    }
}