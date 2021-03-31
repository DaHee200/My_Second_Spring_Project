package com.sparta.loginblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@SpringBootApplication
public class LoginblogApplication {

    //작성시간 한국으로 맞추기
    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }
    SimpleDateFormat sdf = new SimpleDateFormat("MMddYYYY E hhmm a");
//  sdf명.format(날짜값); / MM:월 (숫자) dd:해당월의 몇번째 일 /yyyy:년도 /E:해당 붤의 몇번째 요일 /
//  kk: 해당 날짜의 시간(1-24) mm: 해당 날짜의 분 / a: AM,PM

    public static void main(String[] args) {
        SpringApplication.run(LoginblogApplication.class, args);
    }

}
