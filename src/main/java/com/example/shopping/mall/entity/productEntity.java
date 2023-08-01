package com.example.shopping.mall.entity;

import com.example.shopping.mall.dto.productDto;
import com.example.shopping.mall.dto.userDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Table(name = "products")
public class productEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //다 대 일 관계 유저와 상품 -> 유저가 상품을 등록할수있음.
    @JoinColumn(name = "user_id")
    private userEntity user;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 400)
    private String content;

    @Column(nullable = false, length = 400)
    private int price;

    @Column
    private String img;

    public productDto toDto() {
        return new productDto(id, title, content, price, img);
    }
}
