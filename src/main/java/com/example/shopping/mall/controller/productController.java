package com.example.shopping.mall.controller;
import com.example.shopping.mall.dto.productDto;
import com.example.shopping.mall.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class productController {
    @Autowired
    productService productService;

    @GetMapping("/products")
    public String index(Model model) {
        List<productDto> dtos = productService.index();
        model.addAttribute("productDto", dtos);
        return "products/index";
    }

    @GetMapping("/products/show/{id}")
        public String show(@PathVariable Long id, Model model) {
        productDto dto = productService.show(id);
        model.addAttribute("productDto", dto);
        return "products/show";
    }
}
