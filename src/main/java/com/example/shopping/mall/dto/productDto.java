package com.example.shopping.mall.dto;

import com.example.shopping.mall.entity.productEntity;
import com.example.shopping.mall.entity.userEntity;
import lombok.*;

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
    private String status = "Active";

    public productDto(Long id, String title, String content, int price, String img) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.price = price;
        this.img = img;
    }

    public productDto(Long id, String title, String content, int price, String img, String status) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.price = price;
        this.img = img;
        this.status = status;
    }

    public productEntity toEntity(userEntity user) {
        return new productEntity(id, user, title, content, price, img, status);
    }
}
