package com.example.shopping.mall.service;


import com.example.shopping.mall.dto.userDto;
import com.example.shopping.mall.entity.userEntity;
import com.example.shopping.mall.repository.userRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.Optional;

@Slf4j
@Service
public class userService {
    @Autowired
    private userRepository userRepository;

    @Transactional
    public userDto register(userDto dto) {

        userEntity userEntity = dto.toEntity();
        String username = userEntity.getUsername();

        if (userRepository.findByUsername(username).isPresent()){
            throw new IllegalArgumentException("가입 실패 username이 중복되었습니다.");
        }

        userRepository.save(userEntity);
        return userEntity.toDto();
    }

    public userEntity login(userDto dto) {
        Optional<userEntity> target = userRepository.findByUsername(dto.getUsername());
        if (!target.isPresent()) {
            throw new IllegalArgumentException("가입되지 않은 사용자입니다.");
        }

        userEntity user = target.get();
        if (!user.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        return user;
    }

    public userEntity find(Long userId) {
        return userId != null ? userRepository.findById(userId).orElse(null) : null;
    }
}
