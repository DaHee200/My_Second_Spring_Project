package com.hanghae99.loginbloglast.repository;


import com.hanghae99.loginbloglast.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 회원 ID 중복 확인
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByKakaoId(Long kakaoId);

    User deleteByUsername(String username);

}