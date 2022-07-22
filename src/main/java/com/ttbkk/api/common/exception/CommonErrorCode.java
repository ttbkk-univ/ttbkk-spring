package com.ttbkk.api.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 *  전역적으로 사용되는 예외. ErrorCode 구현체.
 *  Error 추가 : {ErrorName} ({HttpStatus}, {Error 와 함께 내려줄 메시지})
 *
 *  사용법
 *  ex) throw new RestApiException(CommonErrorCode.INVALID_PARAMETER);
 */
@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not exists"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),;

    private final HttpStatus httpStatus;
    private final String message;

}
