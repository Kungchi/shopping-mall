package com.example.shopping.mall.entity;

import com.example.shopping.mall.dto.orderDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Entity
public class orderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id") // 주문하는 유저 아이디
    private userEntity user;

    @ManyToOne
    @JoinColumn(name = "product_id") // 주문하는 상품
    private productEntity product;

    @Column
    private int quantity;

    @Column
    private String adress;

    @Column
    @CurrentTimestamp
    private LocalDateTime order_date;

    public orderDto toDto() {
        return new orderDto(user.getId(), product.getId(), quantity, adress, order_date);
    }
}
