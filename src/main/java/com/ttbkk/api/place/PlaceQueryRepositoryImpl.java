package com.ttbkk.api.place;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

import static com.ttbkk.api.place.QPlace.place;

/**
 * PlaceQueryRepository 을 구현한 구현체.
 * Querydsl 을 이용하여 복잡한 로직 처리.
 * JpaRepository 를 상속받은 인터페이스 혹은 implements 받은 인터페이스의 이름에 "impl" 을 더해서 클래스명을 지어준다. - 규칙
 * */
public class PlaceQueryRepositoryImpl implements PlaceQueryRepository {

    private final JPAQueryFactory queryFactory;

    /**
     * Querydsl 을 사용하기 위해 JPAQueryFactory 생성자 주입.
     * @param entityManager EntityManager 객체
     */
    public PlaceQueryRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    /**
     * Grid 영역 안의 places 와 places 의 수를 return 하는 메서드.
     *
     * @param topRightX topRight 위치의 latitude
     * @param topRightY topRight 위치의 longitude
     * @param botLeftX bottomLeft 위치의 latitude
     * @param botLeftY bottomLeft 위치의 longitude
     * @return PlaceDto.GridResponseDto
     */
    @Override
    public PlaceDto.GridResponseDto getPlacesAndCountInGrid(BigDecimal topRightX, BigDecimal topRightY, BigDecimal botLeftX, BigDecimal botLeftY) {
        List<Place> places = queryFactory
                .selectFrom(place)
                .where(
                        place.latitude.lt(topRightX),
                        place.latitude.gt(botLeftX),
                        place.longitude.lt(topRightY),
                        place.longitude.gt(botLeftY)
                )
                .fetch();

        return new PlaceDto.GridResponseDto(places);
    }
}
