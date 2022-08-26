package com.ttbkk.api.common.exception.domain.user;

import com.ttbkk.api.common.exception.type.ForbiddenException;
import lombok.Getter;

/**
 * User 도메인에서 NOT_FOUND 예외를 어떻게 보여줄건지 정의한 클래스.
 * <p>
 * 찾고자 하는 데이터가 없을때 보여주는 에러.
 */
@Getter
public class NotFoundUser extends ForbiddenException {

    private final String errorCode = "NOT_FOUND_USER";
    private final String message = "User 를 찾을 수 없습니다.";

    /**
     * NotFoundUser 생성자.
     */
    public NotFoundUser() {
    }
}
