package com.example.shopping.mall.api;

import com.example.shopping.mall.dto.productDto;
import com.example.shopping.mall.entity.userEntity;
import com.example.shopping.mall.service.productService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
public class productApiController {
    @Autowired
    productService productService;

    @PostMapping("/api/products") // 상품을 등록하는 RESTAPI
    public ResponseEntity<?> registerProduct(@RequestParam("file") MultipartFile file,
                                             @RequestParam("title") String title,
                                             @RequestParam("content") String content,
                                             @RequestParam("price") int price,
                                             @RequestParam("category") String category,
                                             HttpSession session) {
        try {
            // Thumbnail image handling
            String fileName = productService.Img(file);

            // Handling images in content (TinyMCE Editor)
            content = productService.processContentImages(content);

            // DTO 생성
            productDto dto = new productDto();
            dto.setTitle(title);
            dto.setContent(content);
            dto.setPrice(price);
            dto.setImg(fileName);
            userEntity user = (userEntity) session.getAttribute("user");

            // 서비스 호출
            productService.save(dto, user, category);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
