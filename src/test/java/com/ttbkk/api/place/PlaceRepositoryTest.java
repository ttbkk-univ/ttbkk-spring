package com.ttbkk.api.place;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

/**
 * 도메인 Place 의 Repository Test.
 */
@SpringBootTest
@Transactional
class PlaceRepositoryTest {
    @Autowired
    private PlaceRepository placeRepository;


    /**
     * @BeforEach Test 로직 돌기 전 먼저 실행.
     * Test 전 Test 할 데이터 저장 로직
     */
    @BeforeEach
    void settingTest() {
        Place placeA = Place.builder()
                .name("A")
                .latitude(30.345678912345)
                .longitude(-30.345678912345)
                .isDeleted(false)
                .description("test description1")
                .telephone("000-0000-0000")
                .address("test address1")
                .build();
        Place placeB = Place.builder()
                .name("B")
                .latitude(50.222222222222)
                .longitude(50.222222222222)
                .latitude(30.345678912345)
                .longitude(20.345678912345)
                .isDeleted(false)
                .description("test description2")
                .telephone("000-0000-0000")
                .address("test address2")
                .build();
        Place placeC = Place.builder()
                .name("C")
                .latitude(89.222222222222)
                .longitude(220.222222222222)
                .latitude(30.3456789123451)
                .longitude(-10.345678912345)
                .isDeleted(false)
                .description("test description3")
                .telephone("000-0000-0000")
                .address("test address3")
                .build();

        placeRepository.save(placeA);
        placeRepository.save(placeB);
        placeRepository.save(placeC);
    }

    /**
     * getPlacesAndCountInGrid 메서드 동작 Test.
     */
    @Test
    @DisplayName("getPlacesAndCountInGrid 메서드 동작 Test")
    void getPlacesAndCountInGrid() {
        PlaceDto.GridResponseDto placesAndCountInGrid = placeRepository.getPlacesAndCountInGrid(BigDecimal.valueOf(89.222222222222),
                BigDecimal.valueOf(100.222222222222), BigDecimal.valueOf(-30.222222222222), BigDecimal.valueOf(-30.222222222222));

        assertThat(placesAndCountInGrid.getEdges()).extracting("name").containsOnly("A", "B");
        assertThat(placesAndCountInGrid.getCount()).isEqualTo(placesAndCountInGrid.getEdges().size());
    }
}





