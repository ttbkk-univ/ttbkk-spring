package com.ttbkk.api.place;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class PlaceRepositoryTest {
    @Autowired
    PlaceRepository placeRepository;

    //PlaceRepo 에 데이터 저장 - placeA, placeB, placeC
    @BeforeEach
    void settingTest(){
        Place placeA = Place.builder()
                .name("A")
                .latitude(BigDecimal.valueOf(1.345678912345))
                .longitude(BigDecimal.valueOf(1.345678912345))
                .isDeleted(false)
                .description("test description1")
                .telephone("000-0000-0000")
                .address("test address1")
                .build();
        Place placeB = Place.builder()
                .name("B")
                .latitude(BigDecimal.valueOf(1.345678912345))
                .longitude(BigDecimal.valueOf(1.345678912345))
                .isDeleted(false)
                .description("test description2")
                .telephone("000-0000-0000")
                .address("test address2")
                .build();
        Place placeC = Place.builder()
                .name("C")
                .latitude(BigDecimal.valueOf(13.3456789123451))
                .longitude(BigDecimal.valueOf(132.345678912345))
                .isDeleted(false)
                .description("test description3")
                .telephone("000-0000-0000")
                .address("test address3")
                .build();

        placeRepository.save(placeA);
        placeRepository.save(placeB);
        placeRepository.save(placeC);

    }

    @Test
    void methodTest(){
        Place test = placeRepository.findTest();
        assertThat(test.getName()).isEqualTo("A");
    }


}