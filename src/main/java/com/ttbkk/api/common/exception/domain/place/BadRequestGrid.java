package com.ttbkk.api.common.exception.domain.place;

import com.ttbkk.api.common.exception.type.BadRequestException;
import lombok.Getter;

/**
 * Place 도메인에서 Grid 에 대한 에러.
 * <p>
 * Grid size 가 valid 하지 않을때 내려주는 에러.
 */
@Getter
public class BadRequestGrid extends BadRequestException {
    private final String errorCode = "BAD_REQUEST_GRID";
    private final String message = "올바르지 않은 Grid Size 입니다.";

    /**
     * BadRequestGrid 생성자.
     */
    public BadRequestGrid() {
    }
}
