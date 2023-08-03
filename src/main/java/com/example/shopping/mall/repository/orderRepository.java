package com.example.shopping.mall.repository;

import com.example.shopping.mall.entity.orderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface orderRepository extends JpaRepository<orderEntity, Long> {
    List<orderEntity> findByUserId(Long id);
}
