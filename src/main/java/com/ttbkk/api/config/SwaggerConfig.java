package com.ttbkk.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Application에 있는 모든 api를 찾아서 documentation 해줍니다.
     * Token을 받아서 저장 한 후 Header에 담아 보낼 수 있도록 securitySchemes가 설정 되어 있습니다.
     * @return Docket
     */
    @Bean
    public Docket restAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(
                        Collections.singletonList(
                                this.securityContext()
                        )
                )
                .securitySchemes(
                        Collections.singletonList(
                                this.token()
                        )
                )
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext
                .builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(
                new SecurityReference(
                        "Authorization",
                        authorizationScopes
                )
        );
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("TTBKK Spring Boot REST API")
                .version("1.0.0")
                .description("떡볶이맵 swagger api 입니다.")
                .build();
    }

    private ApiKey token() {
        return new ApiKey(
                "Authorization",
                "Authorization",
                "header"
                );
    }
}
