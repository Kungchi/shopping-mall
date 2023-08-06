package com.example.shopping.mall.controller;
import com.example.shopping.mall.dto.OrderProductDto;
import com.example.shopping.mall.dto.orderDto;
import com.example.shopping.mall.dto.productDto;
import com.example.shopping.mall.dto.userDto;
import com.example.shopping.mall.entity.userEntity;
import com.example.shopping.mall.service.productService;
import com.example.shopping.mall.service.userService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class productController {
    @Autowired
    productService productService;
    @Autowired
    userService userService;

    @GetMapping("/products")
    public String index(Model model, HttpSession session) {
        List<productDto> dtos = productService.index();
        Long userId = (Long) session.getAttribute("userId");
        userEntity userEntity = userService.find(userId);
        

        model.addAttribute("productDto", dtos);
        model.addAttribute("userEntity", userEntity);
        return "products/index";
    }

    @GetMapping("/products/show/{id}")
    public String show(@PathVariable Long id, Model model, HttpSession session) {
        productDto dto = productService.show(id);
        userEntity entity = (userEntity) session.getAttribute("user");


        model.addAttribute("logined", entity != null);
        model.addAttribute("productDto", dto);
        return "products/show";
    }

    @GetMapping("/products/new")
    public String newProduct() {
        return "products/new";
    }

    @GetMapping("/products/productList")
    public String editProduct(HttpSession session, Model model) {
        userEntity user =(userEntity) session.getAttribute("user");
        List<productDto> dtos = productService.findByUser(user.getId());

        model.addAttribute("productDtos", dtos);
        return "products/productList";
    }

}
