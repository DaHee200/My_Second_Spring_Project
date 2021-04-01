package com.hanghae99.loginbloglast.model;


import com.hanghae99.loginbloglast.Timestamped;
import com.hanghae99.loginbloglast.dto.ContentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter //update할때 사용
@Getter // get 함수를 일괄적으로 생성
@NoArgsConstructor//기본 생성자
@Entity// DB 테이블 역할

public class Content extends Timestamped {
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
    private Long userId;


    //name

    //글 생성시 이용
    public Content(ContentRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    //글을 업데이트 할때 가져다 사용
    public Long update(ContentRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        return requestDto.getId();
    }
}

