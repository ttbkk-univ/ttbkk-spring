package com.ttbkk.api.common.exception.type;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * HttpStatus 가 FORBIDDEN 인 예외들의 부모 클래스.
 */
@Getter
public class ForbiddenException extends RuntimeException {

    private final HttpStatus httpStatus = HttpStatus.FORBIDDEN;

    /**
     * ForbiddenException 생성자.
     */
    public ForbiddenException() {
    }
}
