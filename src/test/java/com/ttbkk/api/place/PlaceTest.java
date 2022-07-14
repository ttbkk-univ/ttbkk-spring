package com.ttbkk.api.place;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * 도메인 Place 의 Entity Test.
 */
@Transactional
@SpringBootTest
class PlaceTest {

    @Autowired
    private PlaceRepository placeRepository;

    /**
     * 엔티티 Place 의 필드 BigDecimal data 정상 저장 테스트.
     */
    @Test
    @DisplayName("BigDecimal data 정상 저장 테스트")
    public void placeEntityVerify() {
        Place place = Place.builder()
                .name("test place")
                .latitude(21.214)
                .longitude(213.21412)
                .isDeleted(false)
                .description("test description")
                .telephone("000-0000-0000")
                .address("test address")
                .build();

        placeRepository.save(place);

        List<Place> list = placeRepository.findByName("test place").get();
        Place result = list.get(0);
        assertThat(result.getLatitude()).isEqualTo(BigDecimal.valueOf(21.214));
        assertThat(result.getLongitude()).isEqualTo(BigDecimal.valueOf(213.21412));

        //error test 작성
    }

}
