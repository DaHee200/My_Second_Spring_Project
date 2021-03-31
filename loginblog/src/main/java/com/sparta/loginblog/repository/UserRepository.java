package com.sparta.loginblog.repository;


import com.sparta.loginblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 회원 ID 중복 확인
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByKakaoId(Long kakaoId);

    User deleteByUserName(String username);
}