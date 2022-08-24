package com.ttbkk.api.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 *  특정 도메인에 대한 예외를 구체적으로 내려주는 ErrorCode 구현체.
 *  Error 추가 : {ErrorName} ({HttpStatus})
 *
 *  사용법
 *  ex) throw new BaseException(CustomErrorType.INVALID_COORDINATE_PARAMETER, Custom Message);
 */
@Getter
@RequiredArgsConstructor
public enum CustomErrorType {

    //User
    INVALID_TOKEN(HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(HttpStatus.FORBIDDEN),
    //Brand
    //Place
    INVALID_COORDINATE_PARAMETER(HttpStatus.BAD_REQUEST);

    //Hashtag
    private final HttpStatus httpStatus;
}
