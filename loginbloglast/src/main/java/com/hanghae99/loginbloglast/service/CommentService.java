package com.hanghae99.loginbloglast.service;

import com.hanghae99.loginbloglast.dto.CommentRequestDto;
import com.hanghae99.loginbloglast.model.Comment;
import com.hanghae99.loginbloglast.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import com.hanghae99.loginbloglast.model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

//    public List<Comment> getComment(User user){
//        return commentRepository.findAllByUserOlderByModifiedAtDesc(user);
//    }
    @Transactional
    public Long update_comment(Long id, CommentRequestDto ReqeustDto, com.hanghae99.loginbloglast.model.User user){
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 아이디가 없습니다.")
        );
        comment.update_comment(ReqeustDto, user);
        return comment.getId();

    }

}
