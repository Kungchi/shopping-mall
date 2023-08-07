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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class orderService {

    @Autowired
    orderRepository orderRepository;
    @Autowired
    productRepository productRepository;
    @Autowired
    userRepository userRepository;


    @Transactional
    public orderDto order(orderDto dto) { // 주문한 상품을 데이터베이스에 저장
        Optional<userEntity> userEntity = userRepository.findById(dto.getUser_id());
        Optional<productEntity> productEntity = productRepository.findById(dto.getProduct_id());

        orderEntity target = dto.toEntity(userEntity.get(), productEntity.get());
        orderRepository.save(target);
        return target.toDto();
    }

    public List<orderDto> orderList(Long id) { // 로그인한 유저의 상품 목록을 데이터베이스에서 조회
        List<orderEntity> entities = orderRepository.findByUserId(id);
        List<orderDto> dtos = entities.stream().map(entity -> entity.toDto()).collect(Collectors.toList());
        return dtos;
    }
}
