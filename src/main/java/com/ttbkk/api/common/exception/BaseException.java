package com.ttbkk.api.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Runtime 에서 발생하는 모든 예외들을 받을 수 있는 부모 클래스.
 */
@Getter
public class BaseException extends RuntimeException {

    private HttpStatus httpStatus;
    private String errorCode;
    private String message;

    /**
     * BaseException 생성자.
     */
    public BaseException() {
    }
}
