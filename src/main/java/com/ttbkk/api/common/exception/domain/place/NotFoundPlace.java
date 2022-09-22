package com.ttbkk.api.common.exception.domain.place;

import com.ttbkk.api.common.exception.type.NotFoundException;
import lombok.Getter;

@Getter
public class NotFoundPlace extends NotFoundException {
    private final String errorCode = "NOT_FOUND_PLACE";
    private final String message = "해당 장소는 없는 장소입니다.";

    /**
     * NotFoundPlace 생성자.
     */
    public NotFoundPlace() {
    }
}
