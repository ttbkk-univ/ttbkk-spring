package com.ttbkk.api.user;

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
class UserTest {

    /**
     * entity manager 입니다.
     */
    @Autowired
    private EntityManager entityManager;

    //엔티티 getCreatedAt null 검사 테스트
    @Test
    void userEntityVerify() {
        User user = User.builder()
                .socialId("test id")
                .socialType("test type")
                .build();

        this.entityManager.persist(user);
        System.out.println(user.getCreatedAt());
        System.out.println(user.getUpdatedAt());
        Assertions.assertThat(user.getUpdatedAt()).isNotNull();
    }

}

