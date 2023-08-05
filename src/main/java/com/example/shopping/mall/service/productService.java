package com.example.shopping.mall.service;

import com.example.shopping.mall.dto.productDto;
import com.example.shopping.mall.entity.ProductCategoryEntity;
import com.example.shopping.mall.entity.categoryEntity;
import com.example.shopping.mall.entity.productEntity;
import com.example.shopping.mall.entity.userEntity;
import com.example.shopping.mall.repository.ProductCategoryRepository;
import com.example.shopping.mall.repository.categoryRepository;
import com.example.shopping.mall.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    public List<productDto> index() {
        List<productDto> dtos = productrepository.findAll()
                .stream().map(entity -> entity.toDto()).collect(Collectors.toList());
        return dtos;
    }

    public productDto show(Long id) {
        Optional<productEntity> target = productrepository.findById(id);
        return target.get().toDto();
    }

    public List<productDto> indexById(List<Long> productIds) {
        List<productEntity> entities = productrepository.findByIdIn(productIds);

        Map<Long, productDto> productMap = entities.stream().collect(Collectors.toMap(
                productEntity::getId,
                productEntity::toDto
        ));

        List<productDto> dtos = productIds.stream().map(id -> productMap.get(id)).collect(Collectors.toList());

        return dtos;
    }


    public String Img(MultipartFile file) throws IOException {
        // 프로젝트 루트 경로 가져오기
        String projectPath = new File("").getAbsolutePath();
        // 파일 저장 경로
        String uploadDir = projectPath + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "images" + File.separator;
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        // 파일 이름 생성
        String extension = file.getOriginalFilename() != null ? file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')) : "";
        String fileName = System.currentTimeMillis() + "_" + UUID.randomUUID().toString().replaceAll("[^a-zA-Z0-9]", "") + extension;


        // 파일 저장
        file.transferTo(new File(uploadDir + fileName));
        return fileName;
    }

    public void save(productDto dto, userEntity user, String category) {

        productEntity productEntity = productrepository.save(dto.toEntity(user));
        categoryEntity categoryEntity = categoryRepository.findByName(category);

        ProductCategoryEntity pre = new ProductCategoryEntity(null ,productEntity, categoryEntity);
        productCategoryRepository.save(pre);
    }

}