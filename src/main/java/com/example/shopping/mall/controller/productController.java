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
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class productController {
    @Autowired
    productService productService;
    @Autowired
    userService userService;

    @GetMapping("/products/{id}") // 전 상품 보여주기
    public String index(@PathVariable Long id ,Model model, HttpSession session) {

        List<productDto> dtos = productService.index(id);
        Iterator<productDto> iterator = dtos.iterator();

        while (iterator.hasNext()) {
            productDto dto = iterator.next();
            if(!dto.getStatus().equals("Active")) {
                iterator.remove();
            }
        }

        userEntity userEntity =(userEntity) session.getAttribute("user");
        model.addAttribute("productDto", dtos);
        model.addAttribute("userEntity", userEntity);
        return "products/index";
    }

    @GetMapping("/products/show/{id}") // 상품 자세히 보기
    public String show(@PathVariable Long id, Model model, HttpSession session) {
        productDto dto = productService.show(id);
        userEntity entity = (userEntity) session.getAttribute("user");


        model.addAttribute("logined", entity != null);
        model.addAttribute("productDto", dto);
        return "products/show";
    }

    @GetMapping("/products/new") // 상품을 등록하는 페이지 보여주기 
    public String newProduct() {
        return "products/new";
    }

    @GetMapping("/products/productList") // 로그인한 유저의 등록한 상품 리스트 보여주기
    public String editProduct(HttpSession session, Model model) {
        userEntity user =(userEntity) session.getAttribute("user");
        List<productDto> dtos = productService.findByUser(user.getId());

        model.addAttribute("productDtos", dtos);
        return "products/productList";
    }

    @PostMapping("/api/productList/active/{id}") // 상품을 활성화하냐 비활성화 하냐 하기
    public String active(@PathVariable Long id) {
        productService.active(id);
        return "redirect:/products";
    }

}
