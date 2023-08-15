package com.example.shopping.mall.dto;

import com.example.shopping.mall.entity.commentEntity;
import com.example.shopping.mall.entity.productEntity;
import com.example.shopping.mall.entity.userEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class commentDto {
    private Long id;
    private Long product_id;
    private String content;
    private Long user_id;

    public commentEntity toEntity(userEntity userEntity, productEntity productEntity) {
        return new commentEntity(null, productEntity, content, userEntity);
    }
    public commentEntity toEntity(Long id ,userEntity userEntity, productEntity productEntity) {
        return new commentEntity(id, productEntity, content, userEntity);
    }
}
