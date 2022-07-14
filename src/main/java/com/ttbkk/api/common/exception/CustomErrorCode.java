package com.ttbkk.api.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 *  특정 도메인에 대한 예외를 구체적으로 내려주는 ErrorCode 구현체.
 */
@Getter
@RequiredArgsConstructor
public enum CustomErrorCode implements ErrorCode {

    //User
    //Brand
    //Place
    INVALID_COORDINATE_PARAMETER(HttpStatus.BAD_REQUEST, "좌표값 데이터 포맷을 확인해주세요.");
    //Hashtag

    private final HttpStatus httpStatus;
    private final String message;
}
