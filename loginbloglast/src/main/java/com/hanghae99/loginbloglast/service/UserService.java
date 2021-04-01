package com.hanghae99.loginbloglast.service;

import com.hanghae99.loginbloglast.dto.SignupRequestDto;
import com.hanghae99.loginbloglast.model.User;
import com.hanghae99.loginbloglast.model.UserRole;
import com.hanghae99.loginbloglast.repository.UserRepository;
import com.hanghae99.loginbloglast.security.UserDetailsImpl;
import com.hanghae99.loginbloglast.security.kakao.KakaoOAuth2;
import com.hanghae99.loginbloglast.security.kakao.KakaoUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final KakaoOAuth2 kakaoOAuth2;
    private static final String ADMIN_TOKEN = "/GFeich30/AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";


    //회원가입 기능
    public User registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        //중복된 아이디 정규식 체크
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }
        // 회원 ID 중복 확인

        //닉네임 글자 수 체크
        if (username.length() < 3) {
            throw new IllegalArgumentException("아이디를 3글자 이상 입력해주세요.");
        }
        //{3,20} 3글자부터 최대 20글자까지
        String pattern = "^[a-zA-Z0-9]{3,30}$"; //숫자만

        boolean regex = Pattern.matches(pattern, username);
        //true일때 if문에 걸리면 안되니깐 !사용
        System.out.println(regex);
        if (!regex) {
            throw new IllegalArgumentException("숫자, 소문자, 대문자중 하나는 반드시 포함해주세요!");
        }

        String password = requestDto.getPassword();

        //비밀번호 글자 수 체크과 닉네임이 들어있는지 확인
        if (password.length() < 4 || password.contains(username)) {
            throw new IllegalArgumentException("비밀번호는 닉네임을 포함할 수 없으며, 비밀번호는 4자리 이상이어야 합니다.");
        }

//                비밀번호 다시 체크하기
        String password1 = requestDto.getPassword1();
        System.out.println(password1);
        if (!password1.equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다. 다시 입력해주세요!");
        }
        // 패스워드 인코딩
        password = passwordEncoder.encode(requestDto.getPassword());

        String email = requestDto.getEmail();
        // 사용자 ROLE 확인
        UserRole role = UserRole.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRole.ADMIN;
        }

        User user = new User(username, password, email, role);
        userRepository.save(user);
        return user;
    }



    public void kakaoLogin(String authorizedCode) {
        // 카카오 OAuth2 를 통해 카카오 사용자 정보 조회
        com.hanghae99.loginbloglast.security.kakao.KakaoUserInfo userInfo = kakaoOAuth2.getUserInfo(authorizedCode);
        Long kakaoId = userInfo.getId();
        String nickname = userInfo.getNickname();
        String email = userInfo.getEmail();

        // DB 에 중복된 Kakao Id 가 있는지 확인
        User kakaoUser = userRepository.findByKakaoId(kakaoId)
                .orElse(null);

        // 카카오 정보로 회원가입
        if (kakaoUser == null) {
            // 카카오 이메일과 동일한 이메일을 가진 회원이 있는지 확인
            User sameEmailUser = userRepository.findByEmail(email).orElse(null);
            if (sameEmailUser != null) {
                kakaoUser = sameEmailUser;
                // 카카오 이메일과 동일한 이메일 회원이 있는 경우
                // 카카오 Id 를 회원정보에 저장
                kakaoUser.setKakaoId(kakaoId);
                userRepository.save(kakaoUser);

            } else {
                // 카카오 정보로 회원가입
                // username = 카카오 nickname
                String username = nickname;
                // password = 카카오 Id + ADMIN TOKEN
                String password = kakaoId + ADMIN_TOKEN;
                // 패스워드 인코딩
                String encodedPassword = passwordEncoder.encode(password);
                // ROLE = 사용자
                UserRole role = UserRole.USER;

                kakaoUser = new User(username, encodedPassword, email, role, kakaoId);
                userRepository.save(kakaoUser);
            }
        }

        // 스프링 시큐리티 통해 로그인 처리
        UserDetailsImpl userDetails = new UserDetailsImpl(kakaoUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


}