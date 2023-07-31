package com.example.shopping.mall.api;

import com.example.shopping.mall.dto.productDto;
import com.example.shopping.mall.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class productApiController {
    @Autowired
    productService productService;

    @GetMapping("/api/products")
    public ResponseEntity<List<productDto>> index() {
         List<productDto> dtos = productService.index();
        return dtos != null ? ResponseEntity.status(HttpStatus.OK).body(dtos) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
