package com.hanghae99.loginbloglast.model;

import com.hanghae99.loginbloglast.Timestamped;
import com.hanghae99.loginbloglast.dto.CommentRequestDto;
import lombok.Getter;
import com.hanghae99.loginbloglast.model.User;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Comment extends Timestamped {


    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String content;

    @ManyToOne//댓글 입장에서 유저가 하나
    @JoinColumn(nullable = false)
    private User user;

    public Comment(String contents, User user) {
        this.content = contents;
        this.user = user;
    }

    public void update_comment(CommentRequestDto requestDto, User user) {
        this.user = user;
        this.content = requestDto.getContent();
    }


}
