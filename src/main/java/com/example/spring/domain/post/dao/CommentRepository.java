package com.example.spring.domain.post.dao;

import com.example.spring.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long> {

}
