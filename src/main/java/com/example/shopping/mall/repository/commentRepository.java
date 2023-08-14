package com.example.shopping.mall.repository;

import com.example.shopping.mall.dto.commentDto;
import com.example.shopping.mall.entity.commentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface commentRepository extends JpaRepository<commentEntity, Long> {
    List<commentEntity> findByProductId(Long id);
}
