package com.ttbkk.api.place;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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
                .description("test description1")
                .telephone("000-0000-0000")
                .address("test address1")
                .build();
        Place placeB = Place.builder()
                .name("B")
                .latitude(BigDecimal.valueOf(89.4222322222))
                .longitude(BigDecimal.valueOf(100.42235678912345))
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

    @Test
    @DisplayName("checkLocationFormAndIntegerPart 메서드 검증 Test")
    void checkLocationFormAndIntegerPart() throws Exception {

        String commaError = "90.7,222222222223217839,102.722222222222312312";
        String latitudeError = "890.2222222222223217839,100.222222222222312312";
        String longitudeError = "89.2222222222223217839,-190.222222222222312312";
        String stringError = "89.22222.2222.2223217839,-190.222222222222312312";

        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> {
                    placeService.checkLocationFormAndIntegerPart(commaError);
                })
                .withMessageContaining(",")
                .withNoCause();

        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> {
                    placeService.checkLocationFormAndIntegerPart(latitudeError);
                })
                .withMessageContaining("위도")
                .withNoCause();
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> {
                    placeService.checkLocationFormAndIntegerPart(longitudeError);
                })
                .withMessageContaining("경도")
                .withNoCause();

        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> {
                    placeService.checkLocationFormAndIntegerPart(stringError);
                })
                .withMessageContaining("문자열")
                .withNoCause();
    }

    /**
     * getPlacesAndCountInGrid 메서드 검증 Test.
     */
    @Test
    @DisplayName("getPlacesAndCountInGrid 메서드 검증 Test")
    void getPlacesAndCountInGrid() throws Exception {
        String topRight = "89.7222222222223217839,100.722222222222312312";
        String bottomLeft = "89.2222222222223217839,100.222222222222312312";
        PlaceDto.GridResponseDto placesAndCountInGrid = placeService.getPlacesAndCountInGrid(topRight, bottomLeft);

        assertThat(placesAndCountInGrid.getEdges()).extracting("name").containsOnly("B");
        assertThat(placesAndCountInGrid.getCount()).isEqualTo(placesAndCountInGrid.getEdges().size());
    }

    /**
     * grid size 가 1보다 크기 때문에 exception 발생 예상 테스트.
     *
     * @throws Exception
     */
    @Test
    @DisplayName("verifyGridSize 메서드 검증 Test")
    void verifyGridSize() throws Exception {

        String topRight = "90.7222222222223217839,102.722222222222312312";
        String bottomLeft = "89.2222222222223217839,100.222222222222312312";
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> {
                    placeService.getPlacesAndCountInGrid(topRight, bottomLeft);
                })
                .withMessageContaining("verifyGridSize")
                .withNoCause();
    }

}
