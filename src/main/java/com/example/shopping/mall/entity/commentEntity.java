package com.example.shopping.mall.entity;

import com.example.shopping.mall.dto.commentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Entity(name = "comment")
public class commentEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private productEntity product;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private userEntity user;

    public commentDto toDto() {
        return new commentDto(id, product.getId(), content, user.getId());
    }
}
