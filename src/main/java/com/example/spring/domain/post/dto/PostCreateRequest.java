package com.example.spring.domain.dto;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class PostCreateRequest {

    public static final int POST_TITLE_LENGTH = 50;
    public static final int POST_PASSWORD_LENGTH = 16;

    @NotBlank(message = "게시글 제목을 입력해주세요.")
    @Size(max = POST_TITLE_LENGTH, message = "게시글 제목은 {max}자 이하로 입력해주세요.")
    private String title;

    @NotNull
    private String content;

    @NotNull
    private Boolean isNotice;

    @NotNull
    private Boolean isSecret;

    @Nullable
    @Size(max = POST_PASSWORD_LENGTH, message = "비밀번호는 {max}자 이하로 입력해주세요.")
    private String password;

    @NotNull(message = "카테고리 아이디를 입력해주세요.")
    @PositiveOrZero(message = "올바른 카테고리 아이디를 입력해주세요.")
    private Long categoryId;
}