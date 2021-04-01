package com.hanghae99.loginbloglast.controller;

import com.hanghae99.loginbloglast.Timestamped;
import com.hanghae99.loginbloglast.dto.ContentRequestDto;
import com.hanghae99.loginbloglast.model.Content;
import com.hanghae99.loginbloglast.repository.ContentRepository;
import com.hanghae99.loginbloglast.security.UserDetailsImpl;
import com.hanghae99.loginbloglast.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.tokens.Token;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContentController extends Timestamped {

    //멤버변수 선언
    private final ContentService contentService; // = null;
    private final ContentRepository contentRepository;
//    private final Content content;

    //check content list
    @GetMapping("/api/lists")
    public List<Content> getContent() {
        return contentRepository.findAllByOrderByModifiedAtDesc();
    }


    //아이디에 따라 디테일 페이지로 넘어가기
    @GetMapping("/api/private/{id}")
//    @RequestMapping(value="/api/lists", method = RequestMethod.POST)
    public Content getList(@PathVariable Long id) {
        return contentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
    }

    //write content by the user
    @PostMapping("/api/lists/post/{id}")
    public Content createContent(@RequestBody ContentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        //Request에서 내용가지고 오기
        Content content = new Content(requestDto);
        //유저 아이디 가지고 오기
        content.setUserId(userDetails.getUser().getId());
        return contentRepository.save(content);
    }

    //edit content by the user
    @PutMapping("/api/private/edit/{id}")
    public Long updateContent(@PathVariable Long id, @RequestBody ContentRequestDto requestDto) {
        return contentService.update(id, requestDto);
    }

    //delete content by the user
    @DeleteMapping("/api/private/delete/{id}")
    public Long deleteContent(@PathVariable Long id) {
        contentRepository.deleteById(id);
        return id;
    }
}
