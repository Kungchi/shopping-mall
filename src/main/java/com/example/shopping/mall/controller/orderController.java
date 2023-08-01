package com.example.shopping.mall.controller;

import com.example.shopping.mall.dto.productDto;
import com.example.shopping.mall.dto.userDto;
import com.example.shopping.mall.repository.orderRepository;
import com.example.shopping.mall.repository.productRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class orderController {
    @Autowired
    orderRepository orderRepository;
    @Autowired
    productRepository productRepository;

    @GetMapping("/products/order/{id}")
    public String index(@PathVariable Long id, Model model, HttpSession session) {
        productDto dto = productRepository.findById(id).orElseThrow().toDto();
        Long userId = (Long) session.getAttribute("userId");

        model.addAttribute("productDto", dto);
        model.addAttribute("userId", userId);
        return "products/order";
    }
}
