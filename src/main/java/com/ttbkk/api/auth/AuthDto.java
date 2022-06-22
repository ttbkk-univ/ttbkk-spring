package com.ttbkk.api.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

public class AuthDto {
    @Getter
    @Setter
    @ToString
    public static class SignInRequest {
        @NotEmpty(message = "id 는 필수값입니다")
        public String id;
    }

    public static class SignResult {
        public SignResult(String accessToken) {
            this.accessToken = accessToken;
        }

        public String accessToken;
    }

    @Getter
    @Setter
    @ToString
    public static class VerifyRequest {
        @NotEmpty(message = "accessToken 은 필수값입니다")
        public String accessToken;
    }
}
