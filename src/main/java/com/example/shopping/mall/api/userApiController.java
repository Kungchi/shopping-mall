package com.example.shopping.mall.api;

import com.example.shopping.mall.dto.userDto;
import com.example.shopping.mall.service.userService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class userApiController {

    @Autowired
    private userService userService;

    @PostMapping("/register")
    public ResponseEntity<userDto> register(@RequestBody userDto dto) {
        userDto registered = userService.register(dto);
        return ResponseEntity.status(HttpStatus.OK).body(registered);
    }
}
