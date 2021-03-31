package com.sparta.loginblog.controller;

import com.sparta.loginblog.dto.SignupRequestDto;
import com.sparta.loginblog.model.User;
import com.sparta.loginblog.repository.UserRepository;
import com.sparta.loginblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private UserRepository UserRequestDto;

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    //로그인 에러 페이지
    @GetMapping("/user/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }


    // 회원 가입 요청 처리 & 중복및 오류체크
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto, Model model) {
        try {
            userService.registerUser(requestDto);
        }catch (IllegalArgumentException e){
            System.out.println(e);
            model.addAttribute("message", e.getMessage());
            return "signup";
        }
        return "redirect:/user/login";
    }
//    // 회원 가입 요청 처리 & 중복및 오류체크
//    @PostMapping("/user/signup")
//    public String registerUser(SignupRequestDto requestDto, Model model) {
//
//        try {
//            Optional<User> found = UserRequestDto.findByUsername();
////            User NUser = userService.create(requestDto);
//        } catch (IllegalArgumentException e) {
//            model.addAttribute("error", "true");
//            model.addAttribute("message", "이미 가입된 아이디가 존재합니다.");
//            return "signup";
//        } catch (Exception e) {
//            System.out.println(e);
//            model.addAttribute("message", e.getMessage());
//        }
//        return "redirect:/user/login";
//    }
//
//    //관리자외 접근 방지
//    @GetMapping("/user/forbidden")
//    public String forbidden() {
//        return "forbidden";
//    }

    //카카오
    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(String code) {
        // authorizedCode: 카카오 서버로부터 받은 인가 코드
        userService.kakaoLogin(code);

        return "redirect:/";
}


}