package com.example.shopping.mall.dto;

import com.example.shopping.mall.entity.productEntity;
import com.example.shopping.mall.entity.userEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class productDto {
    private Long id;
    private String title;
    private String content;
    private int price;
    private String img;

    public productEntity toEntity(userEntity user) {
        return new productEntity(id, user, title, content, price, img);
    }
}
