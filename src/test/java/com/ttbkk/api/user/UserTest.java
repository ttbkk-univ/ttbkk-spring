
package com.ttbkk.api.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 * 도메인 User 의 Entity Test.
 */
@Transactional
@SpringBootTest
class UserTest {

    /**
     * entity manager 입니다.
     */
    @Autowired
    private EntityManager entityManager;

    /**
     * 엔티티 getCreatedAt null 검사 테스트.
     */
    @Test
    @DisplayName("User 엔티티 getCreatedAt null 검사 테스트")
    void userEntityVerify() {
        User user = User.builder()
                .socialId("test id")
                .socialType("test type")
                .build();

        this.entityManager.persist(user);
        Assertions.assertThat(user.getUpdatedAt()).isNotNull();
    }

}

