package com.ttbkk.api.common.exception;

import lombok.Getter;

/**
 * CustomerErrorType 으로 생성하여, 예외 처리 할 수 있다.
 *
 * 발생한 예외를 처리해줄 예외 클래스(Exception Class)
 * RuntimeException 상속 받는 이유 : 예외 발생 하면 자동으로 롤백.
 */
@Getter
public class RestApiException extends RuntimeException {

    private CustomErrorType errorType;

    /**
     * RestApiException 생성자.
     *
     * @param errorType : 미리 정의해둔 Error 이름과 HttpStatus.
     * @param message : 해당 에러에 대한 추가적인 메시지.
     */
    public RestApiException(CustomErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

}
