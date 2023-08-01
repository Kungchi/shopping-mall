package com.example.shopping.mall.service;

import com.example.shopping.mall.dto.orderDto;
import com.example.shopping.mall.entity.orderEntity;
import com.example.shopping.mall.entity.productEntity;
import com.example.shopping.mall.entity.userEntity;
import com.example.shopping.mall.repository.orderRepository;
import com.example.shopping.mall.repository.productRepository;
import com.example.shopping.mall.repository.userRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class orderService {

    @Autowired
    orderRepository orderRepository;
    @Autowired
    productRepository productRepository;
    @Autowired
    userRepository userRepository;


    @Transactional
    public orderDto order(orderDto dto) {
        Optional<userEntity> userEntity = userRepository.findById(dto.getUser_id());
        Optional<productEntity> productEntity = productRepository.findById(dto.getProduct_id());

        orderEntity target = dto.toEntity(userEntity.get(), productEntity.get());
        orderRepository.save(target);
        return target.toDto();
    }
}
