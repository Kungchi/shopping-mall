package com.example.shopping.mall.controller;
import com.example.shopping.mall.service.userService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class userController {

    @GetMapping("/login") // 로그인 페이지 보여주기
    public String login() {

        return "login/login";
    }

    @GetMapping("/register") // 회원가입 페이지 보여주기
    public String register() {
        return "login/register";
    }

    @GetMapping("/profile") // profile 보여주기
    public String profile() {
        return "users/profile";
    }
}
