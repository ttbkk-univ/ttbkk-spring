package com.ttbkk.api.auth;

import com.ttbkk.api.user.User;
import com.ttbkk.api.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class AuthService {

    private final JWTService jwtService;

    private final UserService userService;

    /**
     * 클래스 생성자.
     * @param jwtService JWTService
     * @param userService UserService
     */
    public AuthService(JWTService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    /**
     * 로그인 함수.
     * 자세한 내용은 AuthController.signIn 참고.
     *
     * @param authProviderToken google 등으로부터 받은 JWT
     * @return String 우리 서버에서 발급하는 JWT
     * @throws ParseException 파싱 예외처리
     */
    public String signIn(String authProviderToken) throws ParseException {
        Map<String, Object> jwtHashMap = this.jwtService.parse(authProviderToken);
        AuthDto.JwtGoogle googleJwt = new AuthDto.JwtGoogle(jwtHashMap);
        User user = this.userService
            .findBySocialId(googleJwt.getSub())
            .orElse(
                this.userService.create(
                    googleJwt.getSub(),
                    "GOOGLE"
                )
            );
        return this.jwtService.encode(user);
    }
}
