package com.sparta.loginblog.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Getter
@AllArgsConstructor
@NoArgsConstructor // 기본 생성자 생성
public class ContentRequestDto {

    //객체 내에서만 변경이 가능하도록 설정
    private Long id;
    private String title;
    private String content;
    private String name;

//    public void requestDto(JSONObject requestDto) {
//        this.title = requestDto.getString("title");
//        this.content = requestDto.getString("content");
//        this.name = requestDto.getString("name");
//    }


}
