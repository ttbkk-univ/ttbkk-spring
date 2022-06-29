package com.ttbkk.api.auth;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ttbkk.api.user.User;
import com.ttbkk.api.user.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class JWTService {
    private final String secretKey = "secret";

    public String encode(User user) {
        Date now = new Date();
        return Jwts.builder()
                .setIssuer(user.email)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(60).toMillis()))
                .claim("email", user.email)
                .claim("name", user.name)
                .claim("picture", user.picture)
                .signWith(SignatureAlgorithm.HS256, this.secretKey)
                .compact();
    }

    public LinkedHashMap<String, Object> parse(String token) throws ParseException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] chunks = token.split("\\.");
        String jwtBodyString = new String(decoder.decode(chunks[1]));
        JSONParser parser = new JSONParser(jwtBodyString);
        return parser.parseObject();
    }
}
