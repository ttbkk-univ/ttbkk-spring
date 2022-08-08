/**
 * 해시태그를 관리합니다.
 * Place, Brand 등에 연동됩니다.
 */
package com.ttbkk.api.hashtag;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 * 도메인 Hashtag 의 Entity Test.
 */
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
    @DisplayName("엔티티 getCreatedAt null 검사 테스트")
    public void hashtagEntityVerify() {

        Hashtag hashtag = new Hashtag("hashtag");
        this.entityManager.persist(hashtag);
        Assertions.assertThat(hashtag.getCreatedAt()).isNotNull();

    }

}
