package com.sparta.loginblog.service;

import com.sparta.loginblog.dto.ContentRequestDto;
import com.sparta.loginblog.model.Content;
import com.sparta.loginblog.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ContentService {

    private final ContentRepository contentRepository;

    @Autowired // 생성자 ContentService가 생성될때 호출 됨
    public ContentService(ContentRepository contentRepository) {
        //멤버 변수 생성
        this.contentRepository = contentRepository;
    }

    //ID로 글 수정하기
    @Transactional// 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public Long update(Long id, ContentRequestDto requestDto) {
        Content content = contentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        content.update(requestDto);
        return content.getId();
    }


//    @Transactional
//    public Content addcontent(Long contentId,  User user) {
//
//        Long userId = user.getId();
//        Long productUserId = content.getUserId();
//        Long folderUserId = folder.getUser().getId();
//
//        if (!userId.equals(productUserId) || !userId.equals(folderUserId)) {
//            throw new IllegalArgumentException("회원님의 관심상품이 아니거나, 폴더가 아니어서 추가하지 못했습니다.");
//        }
//
//    public Content createContent(ContentRequestDto requestDto, Long userId) {
//    }
}
