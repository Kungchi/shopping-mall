package com.example.shopping.mall.api;

import com.example.shopping.mall.dto.commentDto;
import com.example.shopping.mall.service.commentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class commentApiController {

    @Autowired
    commentService commentService;

    @PostMapping("/api/{id}/comments")
    public ResponseEntity<?> newComment(@PathVariable Long id, @RequestBody commentDto dto) {
        dto.setProduct_id(id);
        commentDto target = commentService.newComment(dto);

        return target != null ? ResponseEntity.status(HttpStatus.OK).body(target) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
