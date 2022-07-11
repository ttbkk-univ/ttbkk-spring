package com.ttbkk.api.brand;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 * 도메인 Brand 의 Entity Test.
 */
@Transactional
@SpringBootTest
public class BrandTest {

    @Autowired
    private EntityManager entityManager;

    /**
     * 엔티티 getCreatedAt null 검사 테스트.
     */
    @Test
    @DisplayName("엔티티 getCreatedAt null 검사 테스트")
    public void brandEntityVerify() {
        Brand brand = Brand.builder()
                .name("정지원")
                .description("test description")
                .build();

        this.entityManager.persist(brand);
        Assertions.assertThat(brand.getUpdatedAt()).isNotNull();
    }

}

