package com.hanghae99.loginbloglast.controller;


import com.hanghae99.loginbloglast.dto.SignupRequestDto;
import com.hanghae99.loginbloglast.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private SignupRequestDto RequestDto;

    //login
    @GetMapping("/user/login")
    public String login() {

        return "login";
    }

    //login error
    @GetMapping("/user/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    //signup
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    //signout

    // 회원 가입 요청 처리 & 중복및 오류체크
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto, Model model) {
        try {
            userService.registerUser(requestDto);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            model.addAttribute("message", e.getMessage());
            return "signup";
        }
        return "redirect:/user/login";
    }
    //카카오
    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(String code) {
        // authorizedCode: 카카오 서버로부터 받은 인가 코드
        userService.kakaoLogin(code);

        return "redirect:/";
    }


}

