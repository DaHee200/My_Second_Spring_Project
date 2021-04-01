package com.hanghae99.loginbloglast.repository;

import com.hanghae99.loginbloglast.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {

    List<Content> findAllByOrderByModifiedAtDesc();
}
