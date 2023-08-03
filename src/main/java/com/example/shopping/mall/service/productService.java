package com.example.shopping.mall.service;

import com.example.shopping.mall.dto.productDto;
import com.example.shopping.mall.entity.productEntity;
import com.example.shopping.mall.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class productService {
    @Autowired
    productRepository productrepository;

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
}