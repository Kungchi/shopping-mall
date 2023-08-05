package com.example.shopping.mall.repository;

import com.example.shopping.mall.entity.categoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface categoryRepository extends JpaRepository<categoryEntity, Long> {
    categoryEntity findByName(String category);
}
