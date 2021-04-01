package com.hanghae99.loginbloglast.controller;

import com.hanghae99.loginbloglast.dto.CommentRequestDto;
import com.hanghae99.loginbloglast.model.Comment;
import com.hanghae99.loginbloglast.model.User;
import com.hanghae99.loginbloglast.repository.CommentRepository;
import com.hanghae99.loginbloglast.security.UserDetailsImpl;
import com.hanghae99.loginbloglast.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Controller
public class CommentController {

    //멤버변수 선언
    private final CommentService commentservice;
    private final CommentRepository commentRepository;

    // 모든 댓글 조회
    @GetMapping("/api/comment/{content_id}")
    public List<Comment> getComment(){

        return commentRepository.findAllByOrderByModifiedAt();
    }
    //edit comment
    // 의존성 주입 DI (스프링이 주입), IoC , IoC 컨트롤러
    // 정보가 과해서 혼란만 줄거같아서 그냥... 스프링이 객체를 넣어주는 마법같은 일이라고 생각하세요
    // 어려운 내용인 거 저도 잘 압니다.. 그쵸 그거땜에 더 헷갈려요
    @PutMapping("/api/comments/{id}")                                                           //매개변수를 넣으면 사용하는 사용자 정보가 들어옴
    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto ReqeustDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentservice.update_comment(id, ReqeustDto, userDetails.getUser());
    }

    @DeleteMapping("/api/comments/{id}")
    public Long deleteComment(@PathVariable Long id){
        commentRepository.deleteById(id);
        return id;
    }

}
