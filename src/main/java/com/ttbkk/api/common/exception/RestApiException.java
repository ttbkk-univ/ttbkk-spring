package com.ttbkk.api.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 발생한 예외를 처리해줄 예외 클래스(Exception Class)
 * RuntimeException 상속 받는 이유 : 예외 발생 하면 자동으로 롤백.
 */
@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException {

    private final ErrorCode errorCode;

}
