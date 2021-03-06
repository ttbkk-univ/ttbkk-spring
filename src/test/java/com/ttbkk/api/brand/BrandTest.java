package com.ttbkk.api.brand;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;


@Transactional
@SpringBootTest
@Commit
public class BrandTest {

    @Autowired
    private EntityManager entityManager;

    /**
     * 엔티티 getCreatedAt null 검사 테스트.
     */
    @Test
    public void brandEntityVerify() {
        Brand brand = Brand.builder()
                .name("정지원")
                .description("test description")
                .build();

        this.entityManager.persist(brand);
        System.out.println(brand.getCreatedAt());
        System.out.println(brand.getUpdatedAt());
        Assertions.assertThat(brand.getUpdatedAt()).isNotNull();
    }

}

