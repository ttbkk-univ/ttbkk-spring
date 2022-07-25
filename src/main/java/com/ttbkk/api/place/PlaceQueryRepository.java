package com.ttbkk.api.place;

import java.math.BigDecimal;
import java.util.List;

/**
 * 복잡한 쿼리들은 SpringDataJpa 가 아닌 Querydsl 로 처리.
 * 복잡한 쿼리들을 처리 하는 인터페이스.
 */
public interface PlaceQueryRepository {
    /**
     * Grid 영역 안의 places 와 places 의 수를 return 하는 메서드.
     *
     * @param topRightX topRight 위치의 latitude
     * @param topRightY topRight 위치의 longitude
     * @param botLeftX  bottomLeft 위치의 latitude
     * @param botLeftY  bottomLeft 위치의 longitude
     * @return PlaceDto.GridResponseDto
     */
    List<Place> getPlacesAndCountInGrid(BigDecimal topRightX, BigDecimal topRightY, BigDecimal botLeftX, BigDecimal botLeftY);

}
