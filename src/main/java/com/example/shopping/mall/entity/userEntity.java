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
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;

    public userDto toDto() {
        return new userDto(username, password, null, email);
    }
}
