package com.ttbkk.api.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;
import java.util.Map;

public class AuthDto {
    @Getter
    @Setter
    @ToString
    public static class SignInRequest {
        @NotEmpty(message = "authProviderToken 은 필수값입니다")
        private String authProviderToken;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class SignInResult {
        private String accessToken;

        /**
         * SignInResult 생성자.
         * @param accessToken User 객체로 생성 된 accessToken
         */
        public SignInResult(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class JwtGoogle {
        /**
         * JwtGoogle 인스턴스 생성자.
         * @param hashmap 파싱 된 Google JWT.
         */
        public JwtGoogle(Map<String, Object> hashmap) {
            this.iss = (String) hashmap.get("iss");
            this.nbf = (BigInteger) hashmap.get("nbf");
            this.aud = (String) hashmap.get("aud");
            this.sub = (String) hashmap.get("sub");
            this.email = (String) hashmap.get("email");
            this.emailVerified = (boolean) hashmap.get("email_verified");
            this.azp = (String) hashmap.get("azp");
            this.name = (String) hashmap.get("name");
            this.picture = (String) hashmap.get("picture");
            this.givenName = (String) hashmap.get("given_name");
            this.iat = (BigInteger) hashmap.get("iat");
            this.exp = (BigInteger) hashmap.get("exp");
            this.jti = (String) hashmap.get("jti");
        }

        private String iss;
        private BigInteger nbf;
        private String aud;
        private String sub;
        private String email;
        private boolean emailVerified;
        private String azp;
        private String name;
        private String picture;
        private String givenName;
        private BigInteger iat;
        private BigInteger exp;
        private String jti;
    }
}
