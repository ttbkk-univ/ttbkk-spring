
package com.ttbkk.api.user;

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

}

