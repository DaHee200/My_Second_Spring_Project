package com.sparta.loginblog.service;

import com.sparta.loginblog.model.User;
import com.sparta.loginblog.repository.UserRepository;
import com.sparta.loginblog.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sun.security.util.Password;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + username));

        return new UserDetailsImpl(user);

    }
//    public UserDetails loadUserByPassword(String Password) throws UsernameNotFoundException {
//        User user = userRepository.findByPassword(Password).orElseThrow(
//                () -> new PasswordNotFoundException(Password +"(를)을 찾을 수가 없습니다."));
//        return new UserDetailsImpl(user);

//    }





}
