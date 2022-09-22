package com.ttbkk.api.common.exception.domain.place;

import com.ttbkk.api.common.exception.type.BadRequestException;
import lombok.Getter;

/**
 * Place 도메인에서 좌표에 대한 BAD_REQUEST 예외를 어떻게 보여줄건지 정의한 클래스.
 * <p>
 * 좌표 데이터가 valid 하지 않을때 내려주는 에러.
 */
@Getter
public class BadRequestLocation extends BadRequestException {
    private final String errorCode = "BAD_REQUEST_LOCATION";
    private final String message = "올바르지 않은 Location 입니다.";

    /**
     * BadRequestLocation 생성자.
     */
    public BadRequestLocation() {
    }
}
