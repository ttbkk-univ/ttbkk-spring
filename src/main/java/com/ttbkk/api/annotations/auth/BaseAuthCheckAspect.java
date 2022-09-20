package com.ttbkk.api.annotations.auth;

import com.ttbkk.api.auth.JWTService;
import com.ttbkk.api.common.exception.domain.user.UnauthorizedUser;
import com.ttbkk.api.user.User;
import com.ttbkk.api.user.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.ParseException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequiredArgsConstructor
public abstract class BaseAuthCheckAspect {
    private static final String AUTHORIZATION = "Authorization";

    /**
     * Bearer 타입 토큰을 사용합니다.
     * "Bearer <JWT>" 형태의 Authorization 에서 <JWT> 부분을 파싱하기 위해 "Bearer " 을 TOKEN_PREFIX 로 정의합니다.
     * 참고: https://datatracker.ietf.org/doc/html/rfc6750
     */
    private static final String TOKEN_PREFIX = "Bearer ";

    /**
     * User.socialId를 JWT의 식별자로 사용합니다.
     */
    private static final String USER_IDENTIFIER_KEY = "socialId";
    private final HttpServletRequest httpServletRequest;
    private final JWTService jwtService;
    private final UserService userService;

    /**
     * request.heder.authorization으로 넘어오는 JWT를 파싱하여 socialId를 얻어오고.
     * socialId로 유저 정보를 조회합니다.
     * @return User 조회 된 유저 정보
     * @throws ParseException JWT 파싱에서 발생할 수 있는 예외.
     */
    protected User getUser() throws ParseException {
        String authorization = httpServletRequest.getHeader(AUTHORIZATION); // HTTP Header 에서 AccessToken을 꺼냄

        // AccessToken이 비어있거나 올바르지 않은 형태인 경우 예외처리
        if (authorization == null
            || !authorization.startsWith(TOKEN_PREFIX)
            || authorization.split(" ").length != 2) {
            throw new UnauthorizedUser();
        }
        Map<String, Object> jwtMap = jwtService.parse(authorization.split(" ")[1]); // Token 검증
        //
        if (!jwtMap.containsKey(USER_IDENTIFIER_KEY)) {
            throw new UnauthorizedUser();
        }
        return userService
                .findBySocialId((String) jwtMap.get(USER_IDENTIFIER_KEY))
                .orElseThrow(() -> new UnauthorizedUser());
    }
}
