package com.example.shopping.mall.entity;

import com.example.shopping.mall.dto.userDto;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Entity
public class userEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 15)
    private String username;

    @Column(nullable = false, length = 40)
    private String password;

    @Column(length = 40)
    private String email;

    public userDto toDto() {
        return new userDto(username, password, null, email);
    }
}
