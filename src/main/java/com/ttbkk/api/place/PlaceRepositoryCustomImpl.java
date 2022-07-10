package com.ttbkk.api.place;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import static com.ttbkk.api.place.QPlace.place;

//JpaRepository 를 상속받은 인터페이스와 이름을 맞춰줘야 한다. - 규칙.
public class PlaceRepositoryCustomImpl implements PlaceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PlaceRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Place findTest() {
        Place result = queryFactory
                .selectFrom(place)
                .where(place.name.eq("A"))
                .fetchOne();

        return result;
    }
}
