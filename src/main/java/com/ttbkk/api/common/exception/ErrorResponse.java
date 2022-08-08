package com.ttbkk.api.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * 실제 client 에게 response 될 클래스.
 * Json 객체로 return 됨. (RestControllerAdvice)
 *
 * @JsonInclude(JsonInclude.Include.NON_EMPTY) : null 값일 경우 json 으로 리턴할때 제외 시킨다.
 */
@Getter
@Builder
public class ErrorResponse {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private HttpStatus httpStatus;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String errorCode;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<ValidationError> errors;

    /**
     * Validated 또는 Valid 로 유효성 검사에 예외가 발생한 경우 예외필드와 그에대한 message response. (예외 가독성을 위한 클래스)
     */
    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class ValidationError {

        private final String field;
        private final String message;

        /**
         *
         * @param fieldError : 해당 예외 FieldError 객체.
         * @return ValidationError 객체.
         */
        public static ValidationError of(final FieldError fieldError) {
            return ValidationError.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
        }
    }
}
