package com.sparta.loginblog.model;

import com.sparta.loginblog.dto.ContentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;

@Setter //update할때 사용
@Getter // get 함수를 일괄적으로 생성
@NoArgsConstructor//기본 생성자
@Entity// DB 테이블 역할
public class Content {
//기본 테이블 만들기

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String name;

    //글 생성시 이용
    public Content(ContentRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.name = requestDto.getName();
    }

    //글을 업데이트 할때 가져다 사용
    public void update(ContentRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.name = requestDto.getName();
    }

//    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
//    public Content updateContent(Long id, ContentRequestDto requestDto) {
//        Content content = contentRepository.findBytitle(title).orElseThrow(
//                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
//        );
//    }

}
