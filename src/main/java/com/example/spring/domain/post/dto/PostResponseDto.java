package com.example.spring.domain.post.dto;

import com.example.spring.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@Data
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

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.visitCount = post.getVisitCount();
        this.isNotice = post.getIsNotice();
        this.isSecret = post.getIsSecret();
        this.password = post.getPassword();
        this.categoryId = post.getCategoryId();
    }
}
