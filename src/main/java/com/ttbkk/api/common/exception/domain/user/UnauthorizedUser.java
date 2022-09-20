package com.ttbkk.api.common.exception.domain.user;

import com.ttbkk.api.common.exception.type.UnauthorizedException;
import lombok.Getter;

/**
 * User 도메인에서 UNAUTHORIZED 예외를 어떻게 보여줄건지 정의한 클래스.
 *
 * 익명 유저의 접근이나, 토큰의 유효성 문제가 있을때 보여주는 에러.
 */
@Getter
public class UnauthorizedUser extends UnauthorizedException {

    private final String errorCode = "UNAUTHORIZED_USER";
    private final String message = "로그인을 해주세요.";

    /**
     * UnauthorizedUser 생성자.
     */
    public UnauthorizedUser() {
    }

}
