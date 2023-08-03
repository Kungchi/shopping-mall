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
    public ResponseEntity<?> register(@RequestBody userDto dto) {
        try {
            userDto registered = userService.register(dto);
            return ResponseEntity.status(HttpStatus.OK).body(registered);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody userDto dto, HttpSession session) {
        try {
            userEntity entity = userService.login(dto);
            session.setAttribute("user", entity);

            userDto logined = entity.toDto();
            return ResponseEntity.ok(logined);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.removeAttribute("user");
        return ResponseEntity.ok().build();
    }


    //혹시 모르니까 만들어놨음
    @GetMapping("/api/user")
    public ResponseEntity<?> getUser(HttpSession session) {
        userEntity user = (userEntity) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        try {
            userDto userDto = user.toDto();
            return ResponseEntity.ok(userDto);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
