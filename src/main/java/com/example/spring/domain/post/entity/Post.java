package com.example.spring.domain.post;

import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Post {

    private static final int MAX_TITLE_LENGTH = 250;
    private static final int MAX_IP_ADDRESS_LENGTH = 128;
    private static final int MAX_PASSWORD_LENGTH = 512;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = MAX_TITLE_LENGTH)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "visit_count", nullable = false)
    private Integer visitCount;

    @Column(name = "is_notice", nullable = false)
    private Boolean isNotice;

    @Column(name = "is_secret", nullable = false)
    private Boolean isSecret;

    @Column(name = "password", length = MAX_PASSWORD_LENGTH)
    private String password;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category categoryId;

    @OneToMany(mappedBy = "post", cascade = REMOVE)
    private final List<Comment> comments = new ArrayList<>();

    // 생성자를 가독성 있게 해줌.
    @Builder
    private Post(String title, String content, Integer visitCount, Boolean isNotice, Boolean isSecret, String password,
                 Category category) {
        this.title = title;
        this.content = content;
        this.visitCount = visitCount;
        this.isNotice = isNotice;
        this.isSecret = isSecret;
        this.password = password;
        this.categoryId = category;
    }
}
