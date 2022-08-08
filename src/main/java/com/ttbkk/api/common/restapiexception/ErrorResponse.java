package com.ttbkk.api.common.restapiexception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * 클라이언트에게 보내줄 error format.
 */
@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {

    private final String code;
    private final String message;

    /**
     * JsonInclude(JsonInclude.Include.NON_EMPTY) : 해당 데이터가 null 이거나 length 가 0 이면 response 되지 않는다.
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<ValidationError> errors;

    /**
     * .@Validate or @Validated 로 에러 발생한 경우, 어느 필드에서 발생 했는지 알려주기 위한 format class.
     */
    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class ValidationError {
        private final String field;
        private final String message;

        /**
         * ValidationError 빌더 생성자.
         * @param fieldError
         * @return ValidationError
         */
        public static ValidationError of(final FieldError fieldError) {
            return ValidationError.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
        }

    }

}
