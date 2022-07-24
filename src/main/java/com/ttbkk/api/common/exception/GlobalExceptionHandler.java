package com.ttbkk.api.common.exception;

import com.ttbkk.api.errors.BaseException;
import com.ttbkk.api.errors.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import org.springframework.validation.BindException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * RestControllerAdvice : 모든 @Controller 에 대한 전역적으로 발생 할 수 있는 예외를 잡아서 처리 가능.
 * RestControllerAdvice 는 응답을 JSON 객체로 리턴 할 수 있기 때문에 선택. (ErrorCode 객체 리턴)
 * ExceptionHandler : 어노테이션을 메서드에 선언하고 특정 예외 클래스를 지정해주면 해당 예외가 발생했을 때 메서드에 정의한 로직을 처리.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * RestApiException 이 발생하면 해당 예외에 대한 ErrorResponse 를 만들고, ResponseEntity 에 담아 클라이언트에게 리턴한다.
     *
     * 예시코드 -> throw new RestApiException(CustomErrorCode.INVALID_COORDINATE_PARAMETER);
     * ErrorCode 구현체를 직접 throw 할 수 있다.
     *
     * @param e RestApiException
     * @return ResponseEntity (body 에는 ErrorResponse 가 담겨져 있고 status 와 함께 리턴)
     */
    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<Object> handleQuizException(final RestApiException e) {
        final ErrorCode errorCode = e.getErrorCode();
        return handleException(errorCode);
    }

    /**
     * IllegalArgumentException 이 발생하면 CommonErrorCode.INVALID_PARAMETER 에 대한 ErrorResponse 를 만들고,
     * ResponseEntity 에 담아 클라이언트에게 리턴한다.
     * RestApiException 과 다른점은 해당 예외에서 나오는 메시지를 ErrorResponse 에 담는다.
     *
     * @param e IllegalArgumentException
     * @return ResponseEntity (body 에는 ErrorResponse 가 담겨져 있고 status 와 함께 리턴)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(final IllegalArgumentException e) {
        log.warn("handleIllegalArgument", e);
        final ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
        return handleException(errorCode, e.getMessage());
    }

    /**
     * IndexOutOfBoundsException 이 발생하면 CommonErrorCode.INVALID_PARAMETER 에 대한 ErrorResponse 를 만들고,
     * ResponseEntity 에 담아 클라이언트에게 리턴한다.
     * RestApiException 과 다른점은 해당 예외에서 나오는 메시지를 ErrorResponse 에 담는다.
     *
     * @param e IndexOutOfBoundsException
     * @return ResponseEntity (body 에는 ErrorResponse 가 담겨져 있고 status 와 함께 리턴)
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<Object> handleIndexOutOfBounds(final IndexOutOfBoundsException e) {
        log.warn("handleIndexOutOfBounds", e);
        final ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
        return handleException(errorCode, e.getMessage());
    }

    /**
     * @Validate or @Validated 로 에러 발생한 경우, CommonErrorCode.INVALID_PARAMETER 에 대한 ErrorResponse 를 만들고,
     * ResponseEntity 에 담아 클라이언트에게 리턴한다.
     *
     * 이후 방안 확정 후 final 키워드 추가 필요 (parameters)
     *
     * @param e the exception
     * @param headers the headers to be written to the response
     * @param status the selected response status
     * @param request the current request
     * @return ResponseEntity (body 에는 ErrorResponse 가 담겨져 있고 status 와 함께 리턴)
     */
//    @Override
//    public ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException e,
//            HttpHeaders headers,
//            HttpStatus status,
//            WebRequest request) {
//        log.warn("handleIllegalArgument", e);
//        final ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
//        return han1dleException(e, errorCode);
//    }

    /**
     * @ExceptionHandler 에 따로 설정하지 않은 예외가 발생하였을때 CommonErrorCode.INTERNAL_SERVER_ERROR 에 대한 ErrorResponse 를 만들고,
     * ResponseEntity 에 담아 클라이언트에게 리턴한다.
     *
     * @param e Exception
     * @return ResponseEntity (body 에는 ErrorResponse 가 담겨져 있고 status 와 함께 리턴).
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllException(final BaseException e) {
        log.warn("handleAllException", e);
        final ErrorCode errorCode = CommonErrorCode.UNEXPECTED_ERROR;
        ExceptionResponse body = new ExceptionResponse(e);
        return ResponseEntity.status(e.getStatus().value())
            .body(body);
    }

    /**
     * 오버로딩 메서드
     * ErrorResponse 객체를 만들어 error status 와 함께 ResponseEntity 객체를 만들어 리턴한다.
     *
     * @param errorCode ErrorCode 인터페이스를 구현한 구현체.
     * @return ResponseEntity (body 에는 ErrorResponse 가 담겨져 있고 status 와 함께 리턴).
     */
    private ResponseEntity<Object> handleException(final ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(errorCode));
    }

    /**
     * 오버로딩 메서드
     * ErrorResponse 객체를 만들어 리턴한다.
     *
     * @param errorCode ErrorCode 인터페이스를 구현한 구현체.
     * @return ErrorResponse
     */
    private ErrorResponse makeErrorResponse(final ErrorCode errorCode) {
        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(errorCode.getMessage())
                .build();
    }

    /**
     * 오버로딩 메서드
     * ErrorResponse 객체를 만들어 error status 와 함께 ResponseEntity 객체를 만들어 리턴한다.
     *
     * @param errorCode ErrorCode 인터페이스를 구현한 구현체.
     * @param message e.getMessage() - 해당 예외에 대한 메시지.
     * @return ResponseEntity (body 에는 ErrorResponse 가 담겨져 있고 status 와 함께 리턴).
     */
    private ResponseEntity<Object> handleException(final ErrorCode errorCode, final String message) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(errorCode, message));
    }

    /**
     * 오버로딩 메서드
     * ErrorResponse 객체를 만들어 리턴한다.
     *
     * @param errorCode ErrorCode 인터페이스를 구현한 구현체.
     * @param message e.getMessage() - 해당 예외에 대한 메시지.
     * @return ErrorResponse
     */
    private ErrorResponse makeErrorResponse(final ErrorCode errorCode, final String message) {
        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(message)
                .build();
    }

    /**
     * 오버로딩 메서드
     * ErrorResponse 객체를 만들어 error status 와 함께 ResponseEntity 객체를 만들어 리턴한다.
     *
     * @param e BindException
     * @param errorCode ErrorCode 인터페이스를 구현한 구현체.
     * @return ResponseEntity (body 에는 ErrorResponse 가 담겨져 있고 status 와 함께 리턴).
     */
    private ResponseEntity<Object> handleException(final BindException e, final ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(e, errorCode));
    }

    /**
     * 오버로딩 메서드
     * ErrorResponse 객체를 만들어 리턴한다.
     *
     * getBindingResult() 메소드를 이용하여 어떤 필드에서 에러가 발생하였는지 알 수 있다.
     * ErrorResponse 의 errors 필드에 데이터 주입.
     *
     * @param e BindException
     * @param errorCode ErrorCode 인터페이스를 구현한 구현체.
     * @return ResponseEntity (body 에는 ErrorResponse 가 담겨져 있고 status 와 함께 리턴).
     */
    private ErrorResponse makeErrorResponse(final BindException e, final ErrorCode errorCode) {
        final List<ErrorResponse.ValidationError> validationErrorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ErrorResponse.ValidationError::of)
                .collect(Collectors.toList());

        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(errorCode.getMessage())
                .errors(validationErrorList)
                .build();
    }

}
