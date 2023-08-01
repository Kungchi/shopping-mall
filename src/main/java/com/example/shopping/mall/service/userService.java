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

        userRepository.save(userEntity);
        return userEntity.toDto();
    }

    public userEntity login(userDto dto) {
        Optional<userEntity> target = userRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());

        userEntity logined = target.get();
        return logined;
    }
}
