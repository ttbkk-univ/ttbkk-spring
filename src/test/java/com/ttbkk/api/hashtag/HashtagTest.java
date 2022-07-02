package com.ttbkk.api.hashtag;

import com.ttbkk.api.domain.hashtag.Hashtag;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@Transactional
@SpringBootTest
public class HashtagTest {

    //엔티티 getCreatedAt null 검사 테스트
    @Test
    public void hashtag_entity_verify(){
        Hashtag hashtag = new Hashtag("hashtag");
        Assertions.assertThat(hashtag.getCreatedAt()).isNotNull();
    }

}
