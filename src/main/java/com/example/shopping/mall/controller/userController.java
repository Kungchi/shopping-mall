package com.example.shopping.mall.controller;
import com.example.shopping.mall.service.userService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class userController {

    @GetMapping("/login")
    public String login() {

        return "login/login";
    }

    @GetMapping("/register")
    public String register() {
        return "login/register";
    }
}
