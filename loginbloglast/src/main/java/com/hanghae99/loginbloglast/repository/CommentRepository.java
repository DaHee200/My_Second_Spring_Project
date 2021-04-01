package com.hanghae99.loginbloglast.repository;

import com.hanghae99.loginbloglast.model.Comment;
import com.hanghae99.loginbloglast.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository <Comment, Long> {

//    List<Comment> findAllByUserOlderByModifiedAtDesc(User user);
    List<Comment> findAllByOrderByModifiedAt();

}

