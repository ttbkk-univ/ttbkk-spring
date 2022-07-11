package com.ttbkk.api.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JpaRepository 인터페이스와 PlaceRepositoryCustom 인터페이스를 상속받는 인터페이스.
 * Spring bean 으로 주입받아 사용되는 인터페이스.
 */
@Repository
public interface PlaceRepository extends JpaRepository<Place, String>, PlaceRepositoryCustom {
}
