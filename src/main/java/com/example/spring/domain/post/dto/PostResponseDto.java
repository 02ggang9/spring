package com.example.spring.domain.dto;

import com.example.spring.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@AllArgsConstructor(access = PRIVATE)
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private Integer visitCount;
    private Boolean isNotice;
    private Boolean isSecret;
    private String password;
    private Long categoryId;

    public static PostResponseDto from(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .visitCount(post.getVisitCount())
                .isNotice(post.getIsNotice())
                .isSecret(post.getIsSecret())
                .password(post.getPassword())
                .categoryId(post.getCategoryId())
                .build();
    }
}
