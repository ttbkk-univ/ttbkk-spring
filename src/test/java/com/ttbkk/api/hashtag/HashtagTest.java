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
@Commit
public class HashtagTest {

    @Autowired
    EntityManager entityManager;

    //엔티티 getCreatedAt null 검사 테스트
    @Test
    public void hashtagEntityVerify() {

        Hashtag hashtag = new Hashtag("hashtag");
        entityManager.persist(hashtag);
        Assertions.assertThat(hashtag.getCreatedAt()).isNotNull();

    }

}
