package com.ttbkk.api.common.restapiexception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 *  특정 도메인에 대한 예외를 구체적으로 내려주는 ErrorCode 구현체.
 *  Error 추가 : {ErrorName} ({HttpStatus}, {Error 와 함께 내려줄 메시지})
 *
 *  사용법
 *  ex) throw new RestApiException(CustomErrorCode.INVALID_COORDINATE_PARAMETER);
 */
@Getter
@RequiredArgsConstructor
public enum CustomErrorCode implements ErrorCode {

    //User
    //Brand
    //Place
    INVALID_COORDINATE_PARAMETER(HttpStatus.BAD_REQUEST, "좌표값 데이터 포맷을 확인해주세요. (데이터 포맷: '{latitude정보}','{longitude정보}')");
    //Hashtag

    private final HttpStatus httpStatus;
    private final String message;
}
