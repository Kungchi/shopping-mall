package com.example.shopping.mall.repository;

import com.example.shopping.mall.entity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<userEntity, Long> {
    Optional<userEntity> findByUsernameAndPassword(String username, String password);
}
