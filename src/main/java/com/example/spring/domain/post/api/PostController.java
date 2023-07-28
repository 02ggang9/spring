package com.example.spring.domain.post.api;

import com.example.spring.domain.application.PostService;
import com.example.spring.domain.post.dto.PostCreateRequest;
import com.example.spring.domain.post.dto.PostResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(
            @PathVariable long postId
    ) {
        PostResponseDto responseDto = postService.getPost(postId);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Void> updatePost(
            @PathVariable long postId,
            @RequestBody @Valid PostCreateRequest requestDto
    ) {
        postService.updatePost(postId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable long postId
    ) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}
