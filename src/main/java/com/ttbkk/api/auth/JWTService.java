package com.ttbkk.api.auth;

import com.ttbkk.api.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class JWTService {
    private final String secretKey = "secret";

    public String encode(User user) {
        Date now = new Date();
        return Jwts.builder()
                .setIssuer(user.id)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(60).toMillis()))
                .claim("id", user.id)
                .signWith(SignatureAlgorithm.HS256, this.secretKey)
                .compact();
    }

    public User parse(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
        return new User((String) claims.get("id"));
    }
}
