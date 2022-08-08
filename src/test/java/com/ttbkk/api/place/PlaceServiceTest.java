package com.ttbkk.api.place;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 도메인 Place 의 Service Test.
 */
@SpringBootTest
@Transactional
class PlaceServiceTest {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private PlaceRepository placeRepository;

    //PlaceRepo 에 데이터 저장 - placeA, placeB, placeC
    @BeforeEach
    void settingTest() {
        Place placeA = Place.builder()
                .name("A")
                .latitude(BigDecimal.valueOf(-10.345678912345))
                .longitude(BigDecimal.valueOf(-10.345678912345))
                .isDeleted(false)
                .description("test description1")
                .telephone("000-0000-0000")
                .address("test address1")
                .build();
        Place placeB = Place.builder()
                .name("B")
                .latitude(BigDecimal.valueOf(50.345678912345))
                .longitude(BigDecimal.valueOf(50.345678912345))
                .isDeleted(false)
                .description("test description2")
                .telephone("000-0000-0000")
                .address("test address2")
                .build();
        Place placeC = Place.builder()
                .name("C")
                .latitude(BigDecimal.valueOf(89.345678912345))
                .longitude(BigDecimal.valueOf(150.345678912345))
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
     * getPlacesAndCountInGrid 메서드 검증 Test.
     */
    @Test
    @DisplayName("getPlacesAndCountInGrid 메서드 검증 Test")
    void getPlacesAndCountInGrid() throws Exception {
        PlaceDto.GridRequestDto requestDto = new PlaceDto.GridRequestDto("89.2222222222223217839,100.222222222222312312", "-30.222222222222432422,-30.222222222222432422");
        PlaceDto.GridResponseDto placesAndCountInGrid = placeService.getPlacesAndCountInGrid(requestDto);

        assertThat(placesAndCountInGrid.getEdges()).extracting("name").containsOnly("A", "B");
        assertThat(placesAndCountInGrid.getCount()).isEqualTo(placesAndCountInGrid.getEdges().size());
    }

//    @Test
//    @DisplayName("verifyGridSize 메서드 검증 Test")
}
