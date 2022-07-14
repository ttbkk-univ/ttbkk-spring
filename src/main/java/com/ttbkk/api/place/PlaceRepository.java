package com.ttbkk.api.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JpaRepository 인터페이스와 PlaceRepositoryCustom 인터페이스를 상속받는 인터페이스.
 * Spring bean 으로 주입받아 사용되는 인터페이스.
 */
@Repository
public interface PlaceRepository extends JpaRepository<Place, String>, PlaceRepositoryCustom {
    /**
     * 해당 name 을 갖고 있는 모든 place 들을 찾는 메서드.
     *
     * @param name place.name
     * @return List<Place>
     */
    Optional<List<Place>> findByName(String name);
}
