package com.example.shopping.mall.dto;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderProductDto {
    private LocalDateTime orderDate;
    private String title;
    private String address;
    private int price;
    private String img;
}
