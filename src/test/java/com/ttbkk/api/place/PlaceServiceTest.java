package com.ttbkk.api.place;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

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
                .latitude(-10.222222222222)
                .longitude(-10.222222222222)
                .isDeleted(false)
                .description("test description1")
                .telephone("000-0000-0000")
                .address("test address1")
                .build();
        Place placeB = Place.builder()
                .name("B")
                .latitude(50.222222222222)
                .longitude(50.222222222222)
                .isDeleted(false)
                .description("test description2")
                .telephone("000-0000-0000")
                .address("test address2")
                .build();
        Place placeC = Place.builder()
                .name("C")
                .latitude(89.222222222222)
                .longitude(220.222222222222)
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
    void getPlacesAndCountInGrid() {
        PlaceDto.GridRequestDto requestDto = new PlaceDto.GridRequestDto("60.2222222222222,130.2222222222222", "-20.2222222222222,-20.2222222222222");
        PlaceDto.GridResponseDto placesAndCountInGrid = placeService.getPlacesAndCountInGrid(requestDto);

        assertThat(placesAndCountInGrid.getEdges()).extracting("name").containsOnly("A", "B");
        assertThat(placesAndCountInGrid.getCount()).isEqualTo(placesAndCountInGrid.getEdges().size());
    }
}
