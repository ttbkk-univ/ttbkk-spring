package com.ttbkk.api.place;
import com.ttbkk.api.common.exception.domain.place.BadRequestGrid;
import com.ttbkk.api.common.exception.domain.place.BadRequestLocation;
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
    void checkLocationFormAndIntegerPart() {

        String commaError = "90.7,222222222223217839,102.722222222222312312";
        String latitudeError = "890.2222222222223217839,100.222222222222312312";
        String longitudeError = "89.2222222222223217839,-190.222222222222312312";
        String stringError = "89.22222.2222.2223217839,-190.222222222222312312";

        assertThatExceptionOfType(BadRequestLocation.class)
                .isThrownBy(() -> {
                    placeService.checkLocationFormAndIntegerPart(commaError);
                })
                .withMessageContaining("Location")
                .withNoCause();

        assertThatExceptionOfType(BadRequestLocation.class)
                .isThrownBy(() -> {
                    placeService.checkLocationFormAndIntegerPart(latitudeError);
                })
                .withMessageContaining("Location")
                .withNoCause();
        assertThatExceptionOfType(BadRequestLocation.class)
                .isThrownBy(() -> {
                    placeService.checkLocationFormAndIntegerPart(longitudeError);
                })
                .withMessageContaining("Location")
                .withNoCause();

        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> {
                    placeService.checkLocationFormAndIntegerPart(stringError);
                })
                .withMessageContaining("Location")
                .withNoCause();
    }

    /**
     * getPlacesAndCountInGrid 메서드 검증 Test.
     */
    @Test
    @DisplayName("getPlacesAndCountInGrid 메서드 검증 Test")
    void getPlacesAndCountInGrid() {
        String topRight = "89.7222222222223217839,100.722222222222312312";
        String bottomLeft = "89.2222222222223217839,100.222222222222312312";
        PlaceDto.GridResponseDto placesAndCountInGrid = placeService.getPlacesAndCountInGrid(topRight, bottomLeft);

        assertThat(placesAndCountInGrid.getEdges()).extracting("name").containsOnly("B");
        assertThat(placesAndCountInGrid.getCount()).isEqualTo(placesAndCountInGrid.getEdges().size());
    }

    /**
     * grid size 가 1보다 크기 때문에 exception 발생 예상 테스트.
     */
    @Test
    @DisplayName("verifyGridSize 메서드 검증 Test")
    void verifyGridSize() {

        String topRight = "88.7222222222223217839,102.722222222222312312";
        String bottomLeft = "87.2222222222223217839,100.222222222222312312";
        assertThatExceptionOfType(BadRequestGrid.class)
                .isThrownBy(() -> {
                    placeService.getPlacesAndCountInGrid(topRight, bottomLeft);
                })
                .withMessageContaining("Grid")
                .withNoCause();
    }

    /**
     * checkLocationDecimalPart 메서드 테스트.
     * 자릿수 짜를때, 반올림.
     */
    @Test
    @DisplayName("checkLocationDecimalPart 메서드 검증 Test")
    void checkAndConvertLocationForm() {
        double[] location1 = {79.23123213124127124124123, 80.21321412412321412412};
        BigDecimal[] result1 = placeService.checkLocationDecimalPart(location1);
        assertThat(result1[0]).isEqualTo(BigDecimal.valueOf(79.2312321312413));
        assertThat(result1[1]).isEqualTo(BigDecimal.valueOf(80.213214124123));
    }

}
