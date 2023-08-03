package com.example.shopping.mall.repository;

import com.example.shopping.mall.entity.orderEntity;
import com.example.shopping.mall.entity.productEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface productRepository extends JpaRepository<productEntity, Long> {
    List<productEntity> findByIdIn(List<Long> productIds);
}
