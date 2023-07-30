package com.example.shopping.mall.service;


import com.example.shopping.mall.dto.userDto;
import com.example.shopping.mall.entity.userEntity;
import com.example.shopping.mall.repository.userRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class userService {
    @Autowired
    private userRepository userRepository;

    @Transactional
    public userDto register(userDto dto) {

        userEntity userEntity = dto.toEntity();

        userRepository.save(userEntity);
        return userEntity.toDto();
    }
}
