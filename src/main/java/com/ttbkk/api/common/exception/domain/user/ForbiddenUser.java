package com.ttbkk.api.common.exception.domain.user;

import com.ttbkk.api.common.exception.type.ForbiddenException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * User 도메인에서 FORBIDDEN_USER 예외를 어떻게 보여줄건지 정의한 클래스.
 * <p>
 * 권한을 가지고 있지 않은 요청을 할때 보여주는 에러.
 */
@Getter
public class ForbiddenUser extends ForbiddenException {

    private final String errorCode = "FORBIDDEN_USER";
    private final String message = "접근 권한이 없습니다.";
    private HttpStatus httpStatus;

    /**
     * ForbiddenUser 생성자.
     */
    public ForbiddenUser() {
        super();
        this.httpStatus = super.getHttpStatus();
    }
}
