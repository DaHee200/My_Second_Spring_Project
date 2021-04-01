package com.hanghae99.loginbloglast.controller;

import com.hanghae99.loginbloglast.model.Comment;
import com.hanghae99.loginbloglast.security.UserDetailsImpl;
import com.hanghae99.loginbloglast.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CommentService commentService;

    @GetMapping("/content/write")
    public String writeContent() {
        return "write";
    }

    @GetMapping("/")
    public String home (Model model , @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if (userDetails != null){
            // 이때가 유저가 로그인한 상태
            // 내가 누군지 알 수 있게된다.
//            List<Comment> comment = commentService.getComment(userDetails.getUser());
//            model.addAttribute("Comment", comment);
//            model.addAttribute("username", userDetails.getUsername());
        }

        return "index";
    }
}
