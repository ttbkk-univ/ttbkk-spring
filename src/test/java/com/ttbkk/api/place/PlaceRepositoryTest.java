package com.ttbkk.api.place;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;

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
                .latitude(BigDecimal.valueOf(-10.345678912345))
                .longitude(BigDecimal.valueOf(-10.345678912345))
                .description("test description1")
                .telephone("000-0000-0000")
                .address("test address1")
                .build();

        Place placeB = Place.builder()
                .name("B")
                .latitude(BigDecimal.valueOf(50.345678912345))
                .longitude(BigDecimal.valueOf(50.345678912345))
                .description("test description2")
                .telephone("000-0000-0000")
                .address("test address2")
                .build();

        Place placeC = Place.builder()
                .name("C")
                .latitude(BigDecimal.valueOf(89.345678912345))
                .longitude(BigDecimal.valueOf(150.345678912345))
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
        List<Place> places = placeRepository.getPlacesAndCountInGrid(BigDecimal.valueOf(89.222222222222), BigDecimal.valueOf(100.222222222222), BigDecimal.valueOf(-30.222222222222), BigDecimal.valueOf(-30.222222222222));
        PlaceDto.GridResponseDto gridResponseDto = new PlaceDto.GridResponseDto(places);

        assertThat(gridResponseDto.getEdges()).extracting("name").containsOnly("A", "B");
        assertThat(gridResponseDto.getCount()).isEqualTo(gridResponseDto.getEdges().size());
    }

    /**
     * getPlacesAndCountInGrid 해당하는 데이터 없을때 NullPointerException 발생하는지 Test.
     */
    @Test
    @DisplayName("NullPointerException 발생하는지 Test")
    void getPlacesAndCountInGridNULL() {
        List<Place> places = placeRepository.getPlacesAndCountInGrid(BigDecimal.valueOf(-90.3213), BigDecimal.valueOf(-130.3213), BigDecimal.valueOf(-80.222222222222), BigDecimal.valueOf(-90.222222222222));
        PlaceDto.GridResponseDto gridResponseDto = new PlaceDto.GridResponseDto(places);

        assertThat(gridResponseDto.getEdges()).isEmpty();
        assertThat(gridResponseDto.getCount()).isEqualTo(0);
    }
}






