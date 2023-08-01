package com.example.shopping.mall.dto;
import com.example.shopping.mall.entity.orderEntity;
import com.example.shopping.mall.entity.productEntity;
import com.example.shopping.mall.entity.userEntity;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class orderDto {
    private Long user_id;
    private Long product_id;
    private int quantity;
    private String address;
    private LocalDateTime order_date;

    public orderEntity toEntity(userEntity userEntity, productEntity productEntity) {
        return new orderEntity(null, userEntity, productEntity, quantity, address, order_date);
    }
}