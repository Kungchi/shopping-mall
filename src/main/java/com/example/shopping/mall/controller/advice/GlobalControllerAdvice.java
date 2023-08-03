package com.example.shopping.mall.controller.advice;

import com.example.shopping.mall.entity.userEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addUserToModel(Model model, HttpSession session) {
        userEntity user = (userEntity) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }
    }
}
