package com.example.shopping.mall.repository;

import com.example.shopping.mall.entity.productEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepository extends JpaRepository<productEntity, Long> {
}
