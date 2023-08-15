package com.example.shopping.mall.api;

import com.example.shopping.mall.dto.commentDto;
import com.example.shopping.mall.service.commentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/api/{id}/comments")
    public ResponseEntity<?> editComment(@PathVariable Long id, @RequestBody commentDto dto) {
        commentDto target = commentService.patch(id ,dto);
        return target != null ? ResponseEntity.status(HttpStatus.OK).body(target) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/api/{id}/comments")
    public  ResponseEntity<?> deleteComment(@PathVariable Long id) {
        commentDto target = commentService.delete(id);

        return target != null ? ResponseEntity.status(HttpStatus.OK).body(target) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
