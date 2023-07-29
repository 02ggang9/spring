package com.example.spring.domain.post.dao;

import com.example.spring.domain.post.entity.Post;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryOld {

    private final EntityManager em;

    public List<Post> findAll() {
        return em.createQuery(
                "select p from Post p", Post.class
        ).getResultList();
    }

}
