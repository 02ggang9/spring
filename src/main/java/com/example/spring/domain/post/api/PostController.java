package com.example.spring.domain.post.api;

import com.example.spring.domain.post.application.PostService;
import com.example.spring.domain.post.dao.PostRepositoryOld;
import com.example.spring.domain.post.dto.PostCreateRequest;
import com.example.spring.domain.post.dto.PostResponseDto;
import com.example.spring.domain.post.entity.Post;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final PostRepositoryOld postRepositoryOld;

    @GetMapping
    public List<PostResponseDto> findPosts() {
        List<Post> posts = postRepositoryOld.findAll();
        return posts.stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());

    }




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
