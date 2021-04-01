package com.hanghae99.loginbloglast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LoginbloglastApplication {


    public static void main(String[] args) {
        SpringApplication.run(LoginbloglastApplication.class, args);
    }

}
