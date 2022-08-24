package com.ttbkk.api.common.exception;

import lombok.Getter;

/**
 * CustomerErrorType 으로 생성하여, 예외 처리 할 수 있다.
 * 상황에 대한 에러 메시지는 다양할 수 있으므로, parameter 로 받아 CustomerErrorType 의 재사용 할 수 있다.
 *
 * 발생한 예외를 처리해줄 예외 클래스(Exception Class)
 * RuntimeException 상속 받는 이유 : 예외 발생 하면 자동으로 롤백.
 */
@Getter
public class BaseException extends RuntimeException {

    private CustomErrorType errorType;

    /**
     * BaseException 생성자.
     *
     * @param errorType : 미리 정의해둔 Error 이름과 HttpStatus.
     * @param message : 해당 에러에 대한 추가적인 메시지.
     */
    public BaseException(CustomErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

}
