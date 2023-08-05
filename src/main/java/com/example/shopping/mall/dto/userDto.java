package com.example.shopping.mall.dto;

import com.example.shopping.mall.entity.userEntity;
import lombok.*;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class userDto {

    private String username;
    private String password;
    private Optional<String> rePassword;
    private String email;

    public userEntity toEntity() {
        return new userEntity(null,false ,username, password, email);
    }
}
