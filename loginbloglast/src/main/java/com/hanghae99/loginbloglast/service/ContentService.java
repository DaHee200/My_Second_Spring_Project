package com.hanghae99.loginbloglast.service;

import com.hanghae99.loginbloglast.dto.ContentRequestDto;
import com.hanghae99.loginbloglast.model.Content;
import com.hanghae99.loginbloglast.repository.ContentRepository;
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
}