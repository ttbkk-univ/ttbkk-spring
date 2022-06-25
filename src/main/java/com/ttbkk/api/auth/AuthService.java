package com.ttbkk.api.auth;

import com.ttbkk.api.user.User;
import com.ttbkk.api.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final JWTService jwtService;
    private final UserService userService;

    public AuthDto.SignInResult signIn(AuthDto.SignInRequest request) throws ParseException {
        LinkedHashMap<String, Object> jwtHashMap = jwtService.parse(request.accessToken);
        AuthDto.JwtGoogle googleJwt = new AuthDto.JwtGoogle(jwtHashMap);
        User user = userService.findByEmail(googleJwt.email);

        if (user == null) {
            user = User.builder()
                    .email(googleJwt.email)
                    .name(googleJwt.name)
                    .picture(googleJwt.picture)
                    .build();
            // 회원가입 로직
            // ...
        }
        String accessToken = jwtService.encode(user);
        return new AuthDto.SignInResult(accessToken);
    }

    public User myInfo(String accessToken) throws ParseException {
        LinkedHashMap<String, Object> jwtHashMap = jwtService.parse(accessToken);
        String email = (String) jwtHashMap.get("email");
        String name = (String) jwtHashMap.get("name");
        String picture = (String) jwtHashMap.get("picture");

        // User user = userService.findByEmail(email);
        // db 에 원래 유저가 생성되어있어야 하지만, db가 없으니 임시로 생성해준다.
        User user = User.builder()
                .email(email)
                .picture(picture)
                .name(name)
                .build();
        return user;
    }
}
