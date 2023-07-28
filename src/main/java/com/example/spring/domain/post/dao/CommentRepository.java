package com.example.spring.domain.dao;

import com.example.spring.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long> {

}
