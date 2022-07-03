package com.ttbkk.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Spring Boot Application Class.
 */
@EnableJpaAuditing
@SpringBootApplication
public class ApiApplication {
    /**
     * 시작 함수.
     * @param args 서버 실행시 전달되는 요소
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
