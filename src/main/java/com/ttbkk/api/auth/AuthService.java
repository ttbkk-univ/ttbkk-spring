package com.ttbkk.api.auth;

import com.ttbkk.api.user.User;
import com.ttbkk.api.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    /**
     * JWT 관련 기능을 담고있는 서비스 입니다.
     */
    @Autowired
    private final JWTService jwtService;

    /**
     * 유저의 CRUD 기능을 제공하는 서비스 입니다.
     */
    @Autowired
    private final UserService userService;

    /**
     * 로그인 함수.
     * 자세한 내용은 AuthController.signIn 참고.
     *
     * @param authProviderToken google 등으로부터 받은 JWT
     * @return String 우리 서버에서 발급하는 JWT
     * @throws ParseException 파싱 예외처리
     */
    public String signIn(String authProviderToken) throws ParseException {
        Map<String, Object> jwtHashMap = jwtService.parse(authProviderToken);
        AuthDto.JwtGoogle googleJwt = new AuthDto.JwtGoogle(jwtHashMap);
        User user = userService
            .findBySocialId(googleJwt.getEmail())
            .orElseGet(
                () -> userService.create(
                    googleJwt.getEmail(),
                    googleJwt.getName(),
                    "GOOGLE"
                )
            );
        return jwtService.encode(user, googleJwt.getPicture());
    }

    /**
     * 내 정보 조회.
     * 자세한 내용은 AuthController.myInfo 참고.
     *
     * @param accessToken 우리 서버에서 발급받은 JWT
     * @return User 토큰으로부터 파싱/조회 된 유저
     * @throws ParseException 파싱 예외처리
     */
    public User myInfo(String accessToken) throws ParseException {
        Map<String, Object> jwtHashMap = jwtService.parse(accessToken);
        String socialId = (String) jwtHashMap.get("socialId");
        String nickname = (String) jwtHashMap.get("nickname");

        return User.builder()
            .socialId(socialId)
            .nickname(nickname)
            .build();
    }
}
