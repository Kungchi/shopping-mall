package com.example.shopping.mall.api;

import com.example.shopping.mall.dto.userDto;
import com.example.shopping.mall.entity.userEntity;
import com.example.shopping.mall.service.userService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Member;
import java.util.Optional;

@Slf4j
@RestController
public class userApiController {

    @Autowired
    private userService userService;

    @PostMapping("/api/register")
    public ResponseEntity<userDto> register(@RequestBody userDto dto) {
        userDto registered = userService.register(dto);
        return ResponseEntity.status(HttpStatus.OK).body(registered);
    }

    @PostMapping("/api/login")
    public ResponseEntity<userDto> login(@RequestBody userDto dto, HttpSession session) {
        userEntity entity = userService.login(dto);
        session.setAttribute("userId", entity.getId());

        userDto logined = entity.toDto();
        return logined != null ? ResponseEntity.status(HttpStatus.OK).body(logined) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
