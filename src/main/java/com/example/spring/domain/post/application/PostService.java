package com.example.spring.domain.post.application;

import com.example.spring.domain.post.dao.PostRepository;
import com.example.spring.domain.post.dao.PostRepositoryOld;
import com.example.spring.domain.post.dto.PostCreateRequest;
import com.example.spring.domain.post.dto.PostResponseDto;
import com.example.spring.domain.post.entity.Post;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final PostRepositoryOld postRepositoryOld;

    @Transactional
    public void createPost(PostCreateRequest requestDto) {
        postRepository.save(Post.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .visitCount(0)
                .isNotice(requestDto.getIsNotice())
                .isSecret(requestDto.getIsSecret())
                .password(requestDto.getPassword())
                .categoryId(requestDto.getCategoryId())
                .build());
    }

    public PostResponseDto getPost(long postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        return PostResponseDto.from(post);
    }

    @Transactional
    public void updatePost(long postId, @Valid PostCreateRequest requestDto) {
        Post post = postRepository.findById(postId).orElseThrow();

        Post newPost = Post.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .visitCount(post.getVisitCount())
                .isNotice(requestDto.getIsNotice())
                .isSecret(requestDto.getIsSecret())
                .password(requestDto.getPassword())
                .categoryId(requestDto.getCategoryId())
                .build();

        post.update(newPost); // @Transactional 에 의해 변경감지 해줌! DB 값 변경 됨!
    }

    @Transactional
    public void deletePost(long postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        postRepository.delete(post);
    }

    public Post getSecretPost(Long postId, String password) {
        Post secretPost = postRepositoryOld.findOne(postId);
        if (!checkSecretPost(secretPost)) {
            throw new RuntimeException("비밀글이 아님");
        }

        if (!checkAccessibleSecretPost(secretPost, password)) {
            throw new RuntimeException("비밀번호가 틀림");
        }
        return secretPost;

    }

    private Boolean checkSecretPost(Post secretPost) {
        return secretPost.getIsSecret();
    }

    private Boolean checkAccessibleSecretPost(Post secretPost, String password) {
        return Objects.equals(secretPost.getPassword(), password);
    }


}
