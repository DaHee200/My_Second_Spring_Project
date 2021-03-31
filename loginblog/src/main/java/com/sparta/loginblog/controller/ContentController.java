package com.sparta.loginblog.controller;

import com.sparta.loginblog.dto.ContentRequestDto;
import com.sparta.loginblog.model.Content;
import com.sparta.loginblog.Timestamped;
import com.sparta.loginblog.repository.ContentRepository;
import com.sparta.loginblog.security.UserDetailsImpl;
import com.sparta.loginblog.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController() //JSON으로 데이터를 주고 받음을 선언합니다.
public class ContentController extends Timestamped {
    //멤버변수 선언
    private final ContentService contentService; // = null;
    private final ContentRepository contentRepository;

    @Autowired //JSON으로 데이터를 주고 받음
    public ContentController(ContentRepository contentRepository, ContentService contentService){
        //멤버변수 생성
        this.contentService = contentService;
        this.contentRepository = contentRepository;
    }


     //게시물 목록 조회
//    @Override
//    public List<Content> list() throws Exception {
//        return contentRepository.findByTitle(title);
//    }

//    등록된 글 목록  조회하기
    @GetMapping("/api/lists")
    public List<Content> getContent(){
//        LocalDateTime start = LocalDateTime.now().minusDays(1);
//        LocalDateTime end = LocalDateTime.now();
        return contentRepository.findAllByOrderByModifiedAtDesc();
    }

    //새로운글 등록
    @PostMapping("/api/bloglists")
    public Content createBlog(@RequestBody ContentRequestDto requestDto){
        Content content = new Content(requestDto);
        return contentRepository.save(content);

    }

//    //새로운 글 등록하기
//    @PostMapping("/api/private/edit/{id}")
//    public Content createContent(@RequestBody ContentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
//       //로그인 되어 있는 id
//        Long userId = userDetails.getUser().getId();
//
//        Content content = contentService.creatContent(requestDto, userId);
//        //응답보내기
//        return content;
//
//    }

    //아이디에 따라 디테일 페이지로 넘어가기
    @GetMapping("/api/private/{id}")
//    @RequestMapping(value="/api/lists", method = RequestMethod.POST)
    public Content getList(@PathVariable Long id) {
        return contentRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
    }

    //등록된글 수정하기
    @PutMapping("/api/private/edit/{id}")
    public Long updatecontent(@PathVariable Long id, @RequestBody ContentRequestDto requestDto){
        return contentService.update(id,requestDto);
    }

    //등록된글 삭제하기
    @DeleteMapping("/api/private/edit/{id}")
    public Long deleteContent(@PathVariable Long id) {
        contentRepository.deleteById(id);
        return id;
    }
}
