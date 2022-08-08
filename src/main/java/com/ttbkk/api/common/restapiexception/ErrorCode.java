package com.ttbkk.api.common.restapiexception;

import org.springframework.http.HttpStatus;

/**
 * 예외(Error) 정의 인터페이스.
 */
public interface ErrorCode {

    /**
     * 해당 예외의 이름을 불러오기 위한 메서드.
     * @return String
     */
    String name();

    /**
     * 해당 예외의 HttpStatus 불러오기 위한 메서드.
     * @return HttpStatus
     */
    HttpStatus getHttpStatus();

    /**
     * 해당 예외의 Message 를 불러오기 위한 메서드.
     * @return String
     */
    String getMessage();
}


