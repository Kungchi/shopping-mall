package com.example.shopping.mall.service;

import com.example.shopping.mall.dto.productDto;
import com.example.shopping.mall.entity.productEntity;
import com.example.shopping.mall.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class productService {
    @Autowired
    productRepository productrepository;

    public List<productDto> index() {
        List<productDto> dtos = productrepository.findAll()
                .stream().map(entity -> entity.toDto()).collect(Collectors.toList());
        return dtos;
    }
}
