package com.sparta.loginblog.repository;


import com.sparta.loginblog.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {

    //생성/수정 시간대로 내림차순으로 나오게 만들기
    default List<Content> findAllByOrderByModifiedAtDesc() {
        return findAllByOrderByModifiedAtDesc();
    }

    List<Content> findByTitle(String title);
}
    //    List<Content> findByTitle(title);
//List<Content> findAllByOrderByModifiedAtDesc(LocalDateTime start, LocalDateTime end);
//}
