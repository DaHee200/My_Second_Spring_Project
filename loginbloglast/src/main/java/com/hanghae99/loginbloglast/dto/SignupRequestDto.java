package com.hanghae99.loginbloglast.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {
    //로그인 틀 보내주기
    private String username;
    private String password;
    private String password1;
    private String email;
    private boolean admin = false;
    private String adminToken = "";

}
