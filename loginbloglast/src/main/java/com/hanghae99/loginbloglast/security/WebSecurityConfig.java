package com.hanghae99.loginbloglast.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //paswordEncoder에서 "Consider defining a bean of type"에러가 나서 넣음 / 'BCrypt' 해시함수
    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                // image 폴더를 login 없이 허용
                .antMatchers("/images/**").permitAll()
                // css 폴더를 login 없이 허용
                .antMatchers("/css/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/api/comments").permitAll()
                .antMatchers("/api/comments").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/api/comment/{content_id").permitAll()
                .antMatchers("/api/private/{id}").permitAll()
                .antMatchers("/resources/templates/private").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                // 그 외 모든 요청은 인증과정 필요
                .anyRequest().authenticated()
                .and()
                //적어도 로그인 페이지는 허용
                .formLogin()
                .loginPage("/user/login")
                .failureUrl("/user/login/error")
                .loginProcessingUrl("/user/login")
                //로그인이 완료되었을때 위치
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                //로그아웃 기능도 허용
                .logout()
                .logoutUrl("/user/logout")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/user/forbidden");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

