package com.ttbkk.api.auth;

import com.ttbkk.api.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class JWTService {

    /**
     * 유저 데이터를 JWT로 암호화 하는 함수.
     * @param user User 객체
     * @return String 생성된 JWT String
     */
    public String encode(User user) {
        Date now = new Date();
        String secretKey = "secret"; // 토큰 변경에 필요한 secret key
        int expireSeconds = 60 * 60; // 토큰의 만료 기간
        return Jwts.builder()
                .setIssuer("https://api.ttbkk.com")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(expireSeconds).toMillis()))
                .claim("id", user.getId())
                .claim("socialId", user.getSocialId())
                .claim("role", user.getRole())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * JWT String 을 Map 으로 파싱해주는 함수.
     * @param token JWT String
     * @return Map<String, Object> key, value 형태의 Map 데이터
     * @throws ParseException 파싱 실패 예외처리
     */
    public Map<String, Object> parse(String token) throws ParseException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] chunks = token.split("\\.");
        String jwtBodyString = new String(decoder.decode(chunks[1]));
        JSONParser parser = new JSONParser(jwtBodyString);
        return parser.parseObject();
    }
}
