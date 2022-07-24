package com.ttbkk.api.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Optional;

/**
 *
 */
@Getter
public abstract class BaseException extends Throwable {
    private final HttpStatus status;
    private final String message;

    public BaseException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
