package com.example.shopping.mall.controller.advice;

import com.example.shopping.mall.entity.userEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addUserToModel(Model model, HttpSession session) { // 전 페이지에서 로그인한 정보를 쓸수있게 등록
        userEntity user = (userEntity) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }
    }
}
