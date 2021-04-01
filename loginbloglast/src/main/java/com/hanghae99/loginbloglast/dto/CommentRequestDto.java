package com.hanghae99.loginbloglast.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.hanghae99.loginbloglast.model.Comment;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Getter
@Setter
public class CommentRequestDto {
    private Long id;
    private String content;
    private String user;

}
