/**
 * 해시태그를 관리합니다.
 * Place, Brand 등에 연동됩니다.
 */
package com.ttbkk.api.hashtag;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Transactional
@SpringBootTest
public class HashtagTest {

    /**
     * entity manager.
     */
    @Autowired
    private EntityManager entityManager;

    /**
     * 엔티티 getCreatedAt null 검사 테스트.
     */
    @Test
    public void hashtagEntityVerify() {

        Hashtag hashtag = new Hashtag("hashtag");
        this.entityManager.persist(hashtag);
        Assertions.assertThat(hashtag.getCreatedAt()).isNotNull();

    }

}
