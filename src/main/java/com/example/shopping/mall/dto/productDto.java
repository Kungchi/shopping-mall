package com.example.shopping.mall.dto;

import com.example.shopping.mall.entity.productEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class productDto {
    private Long id;
    private String title;
    private String content;
    private int price;
    private String img;
}
