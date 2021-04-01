package com.hanghae99.loginbloglast.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor // 기본 생성자 생성
public class ContentRequestDto {
    //for the content
    //객체 내에서만 변경이 가능하도록 설정
    private Long id;
    private String title;
    private String content;
}