package com.example.spring.domain.api;

import com.example.spring.domain.application.PostService;
import com.example.spring.domain.dto.PostCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(
            @RequestBody @Valid PostCreateRequest requestDto
    ) {
        postService.createPost(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }
}
