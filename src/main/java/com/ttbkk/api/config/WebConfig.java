package com.ttbkk.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * CORS 설정을 추가합니다. CORS 에 대한 자세한 설명은 아래 링크부터 시작하세요.
     * https://developer.mozilla.org/ko/docs/Web/HTTP/CORS
     * @param registry 넘겨받은 registry 에 허용하고자 하는 사이트를 추가해줍니다.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost",
                        "http://localhost:63342"
                        );
    }
}
