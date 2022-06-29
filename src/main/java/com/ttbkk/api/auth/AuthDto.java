package com.ttbkk.api.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;
import java.util.LinkedHashMap;

public class AuthDto {
    @Getter
    @Setter
    @ToString
    public static class SignInRequest {
        @NotEmpty(message = "accessToken 는 필수값입니다")
        public String accessToken;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class SignInResult {
        String accessToken;

        public SignInResult(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class JwtGoogle {
        public JwtGoogle(LinkedHashMap<String, Object> hashmap) {
            this.iss = (String) hashmap.get("iss");
            this.nbf = (BigInteger) hashmap.get("nbf");
            this.aud = (String) hashmap.get("aud");
            this.sub = (String) hashmap.get("sub");
            this.email = (String) hashmap.get("email");
            this.email_verified = (boolean) hashmap.get("email_verified");
            this.azp = (String) hashmap.get("azp");
            this.name = (String) hashmap.get("name");
            this.picture = (String) hashmap.get("picture");
            this.given_name = (String) hashmap.get("given_name");
            this.iat = (BigInteger) hashmap.get("iat");
            this.exp = (BigInteger) hashmap.get("exp");
            this.jti = (String) hashmap.get("jti");
        }

        String iss;
        BigInteger nbf;
        String aud;
        String sub;
        String email;
        boolean email_verified;
        String azp;
        String name;
        String picture;
        String given_name;
        BigInteger iat;
        BigInteger exp;
        String jti;
    }
}
