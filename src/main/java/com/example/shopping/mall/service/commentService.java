package com.example.shopping.mall.service;

import com.example.shopping.mall.dto.commentDto;
import com.example.shopping.mall.entity.commentEntity;
import com.example.shopping.mall.entity.productEntity;
import com.example.shopping.mall.entity.userEntity;
import com.example.shopping.mall.repository.commentRepository;
import com.example.shopping.mall.repository.productRepository;
import com.example.shopping.mall.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class commentService {

    @Autowired
    commentRepository commentRepository;

    @Autowired
    productRepository productRepository;

    @Autowired
    userRepository userRepository;

    public commentDto newComment(commentDto dto) {
        if(dto.getContent().isEmpty()) {
            return null;
        }
        userEntity user = userRepository.findById(dto.getUser_id()).get();
        productEntity product = productRepository.findById(dto.getProduct_id()).get();

        commentEntity target = dto.toEntity(user, product);
        commentRepository.save(target);

        return target.toDto();
    }

    public List<commentDto> index(Long id) {
        List<commentEntity> entities = commentRepository.findByProductId(id);

        return entities.stream().map(entity -> entity.toDto()).collect(Collectors.toList());
    }

    public commentDto patch(Long id, commentDto dto) {
        productEntity product = productRepository.findById(dto.getProduct_id()).orElseThrow(() -> new IllegalArgumentException("리뷰 수정 실패, 상품이 없습니다."));
        userEntity user = userRepository.findById(dto.getUser_id()).orElseThrow(() -> new IllegalArgumentException("리뷰 수정 실패, 유저가 없습니다."));
        commentEntity comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("리뷰 수정 실패, 리뷰가 없습니다."));

        if(product != null && user != null && comment != null) {
            commentRepository.save(dto.toEntity(id ,user, product));
            return dto;
        }
        else {
            return null;
        }
    }

    public commentDto delete(Long id) {
        commentEntity target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("리뷰가 없습니다."));
        if(target != null) {
            commentRepository.deleteById(id);
            return target.toDto();
        }
        else {
            return null;
        }
    }
}
