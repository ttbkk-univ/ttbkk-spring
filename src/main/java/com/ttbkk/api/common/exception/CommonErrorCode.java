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

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "서버에서 요청을 이해할수 없습니다. 요청을 확인해주세요."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "서버에서 요청받은 리소스를 찾을 수 없습니다."),
    UNEXPECTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "예상치 못한 에러가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
