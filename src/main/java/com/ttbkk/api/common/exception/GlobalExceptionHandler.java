package com.ttbkk.api.common.exception;

import com.ttbkk.api.common.exception.ErrorResponse.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * RestControllerAdvice : 모든 @Controller 에 대한 전역적으로 발생 할 수 있는 예외를 잡아서 처리 가능.
 * RestControllerAdvice 는 응답을 JSON 객체로 리턴 할 수 있기 때문에 선택. (ErrorCode 객체 리턴)
 * ExceptionHandler : 어노테이션을 메서드에 선언하고 특정 예외 클래스를 지정해주면 해당 예외가 발생했을 때 메서드에 정의한 로직을 처리.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * BaseException 으로 throw 한 예외들 처리 하는 메서드.
     * @param e BaseException
     * @return ErrorResponse
     */
    @ExceptionHandler(BaseException.class)
    public ErrorResponse handleRestApiException(BaseException e) {
        log.error("BaseException", e);
        return ErrorResponse.builder()
                .httpStatus(e.getHttpStatus())
                .errorCode(e.getErrorCode())
                .message(e.getMessage())
                .build();
    }

    /**
     * 예상하지 못한 예외들 처리.
     * 즉 throw 하지 못한 예외들. Runtime 중에 발생하는 모든 예외들 처리.
     * @param e RuntimeException
     * @return ErrorResponse
     */
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse handleRuntimeException(RuntimeException e) {
        log.error("RuntimeException", e);
        return ErrorResponse.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorCode("UNEXPECTED_ERROR")
                .message(e.getMessage())
                .build();
    }

    /**
     * Validated 또는 Valid 의 유효성검사를 통해 발생되는 예외 처리 메서드.
     * @param e ConstraintViolationException
     * @return ErrorResponse
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
        log.error("ConstraintViolationException", e);
        return ErrorResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .errorCode("BAD_REQUEST_EXCEPTION")
                .errors(getResultMessage(e.getConstraintViolations().iterator()))
                .build();
    }

    /**
     * 발생한 예외를 Iterator 로 돌려서, ValidationError 객체를 담은 리스트 만들어주는 클래스.
     * 여러 필드에서 예외가 발생한 경우도 처리해주기 위하여 List 로 선택.
     *
     * @param violationIterator
     * @return List<ValidationError>
     */
    private List<ValidationError> getResultMessage(final Iterator<ConstraintViolation<?>> violationIterator) {
        List<ValidationError> errorList = new ArrayList<>();
        while (violationIterator.hasNext()) {
            final ConstraintViolation<?> constraintViolation = violationIterator.next();
            ValidationError error = ValidationError.builder()
                    .field(getPropertyName(constraintViolation.getPropertyPath().toString()))
                    .message(constraintViolation.getMessage())
                    .build();
            errorList.add(error);
        }
        return errorList;
    }

    /**
     * Validation field 를 뽑아내기 위한 메서드.
     * @param propertyPath
     * @return field(String)
     */
    private String getPropertyName(String propertyPath) {
        // 전체 속성 경로에서 속성 이름만 가져온다.
        return propertyPath.substring(propertyPath.lastIndexOf('.') + 1);
    }
}

