package com.example.shopping.mall.api;

import com.example.shopping.mall.dto.orderDto;
import com.example.shopping.mall.service.orderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class orderApiController {

    @Autowired
    orderService orderService;

    @PostMapping("/api/order/{id}")
    public ResponseEntity<orderDto> order(@PathVariable Long id, @RequestBody orderDto dto) {
        orderDto ordered = orderService.order(dto);
        return ordered != null ? ResponseEntity.status(HttpStatus.OK).body(ordered) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
