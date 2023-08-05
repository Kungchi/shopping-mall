package com.example.shopping.mall.controller;

import com.example.shopping.mall.dto.OrderProductDto;
import com.example.shopping.mall.dto.orderDto;
import com.example.shopping.mall.dto.productDto;
import com.example.shopping.mall.dto.userDto;
import com.example.shopping.mall.entity.userEntity;
import com.example.shopping.mall.repository.orderRepository;
import com.example.shopping.mall.repository.productRepository;
import com.example.shopping.mall.service.orderService;
import com.example.shopping.mall.service.productService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class orderController {
    @Autowired
    orderService orderService;
    @Autowired
    productService productService;

    @GetMapping("/products/order/{id}")
    public String index(@PathVariable Long id, Model model, HttpSession session) {
        productDto dto = productService.show(id);
        userEntity userEntity = (userEntity) session.getAttribute("user");

        model.addAttribute("productDto", dto);
        model.addAttribute("userId", userEntity.getId());
        return "products/order";
    }

    @GetMapping("/products/order/{id}/list")
    public String orderList(@PathVariable Long id, Model model) {
        List<orderDto> dtos = orderService.orderList(id);
        List<Long> productId = dtos.stream().map(dto -> dto.getProduct_id()).collect(Collectors.toList());
        List<productDto> productDtos = productService.indexById(productId);

        List<OrderProductDto> orderProductDtos = new ArrayList<>();
        for (int i = 0; i < dtos.size(); i++) {
            OrderProductDto orderProductDto = new OrderProductDto();
            orderProductDto.setId(productId.get(i));
            orderProductDto.setOrderDate(dtos.get(i).getOrder_date());
            orderProductDto.setTitle(productDtos.get(i).getTitle());
            orderProductDto.setAddress(dtos.get(i).getAddress());
            orderProductDto.setPrice(productDtos.get(i).getPrice());
            orderProductDto.setImg(productDtos.get(i).getImg());
            orderProductDtos.add(orderProductDto);
        }
        model.addAttribute("orderProductDtos", orderProductDtos);

        return "products/orderList";
    }
}
