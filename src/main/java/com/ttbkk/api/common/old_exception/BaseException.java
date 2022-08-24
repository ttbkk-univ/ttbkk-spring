package com.ttbkk.api.common.old_exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    /**
     * 기본 BaseException Wrapper.
     */
    public BaseException() {
    }

    /**
     * 메시지를 포함하는 Base Exception Wrapper.
     * @param message 커스텀 에러 메시지.
     */
    public BaseException(String message) {
        super(message);
    }
}
