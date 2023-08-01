package com.example.shopping.mall.repository;

import com.example.shopping.mall.entity.orderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface orderRepository extends JpaRepository<orderEntity, Long> {
}
