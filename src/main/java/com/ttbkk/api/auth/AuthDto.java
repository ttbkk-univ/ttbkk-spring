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
         * 필수값이면서 유저를 식별 가능한 값은 sub이므로, 해당 값을 우리 서버의 유저 식별자로 사용한다.
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

        private String iss; // Issuer (who created and signed this token)
        private BigInteger nbf; // Not valid before (seconds since Unix epoch)
        private String aud; // Audience (who or what the token is intended for)
        private String sub; // Subject (whom the token refers to)
        private String email;
        private boolean emailVerified;
        private String azp; // Authorized party (the party to which this token was issued)
        private String name;
        private String picture;
        private String givenName;
        private BigInteger iat; // Issued at (seconds since Unix epoch)
        private BigInteger exp; // Expiration time (seconds since Unix epoch)
        private String jti; // JWT ID (unique identifier for this token)
    }
}
