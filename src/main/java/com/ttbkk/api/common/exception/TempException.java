package com.ttbkk.api.common.exception;

import lombok.Getter;

@Getter
public class TempException extends RuntimeException {
    /**
     * 기본 TempException Wrapper.
     */
    public TempException() {
    }

    /**
     * 메시지를 포함하는 Base Exception Wrapper.
     * @param message 커스텀 에러 메시지.
     */
    public TempException(String message) {
        super(message);
    }
}
