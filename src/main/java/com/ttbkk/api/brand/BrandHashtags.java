package com.ttbkk.api.brand;

import com.ttbkk.api.hashtag.Hashtag;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Brand와 Hashtag의 N:M 관계를 구현하기 위한 중간 테이블입니다.
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "brand_hashtags",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "brand_hashtags_brand_id_hashtag_id_696de634_uniq",
                        columnNames = {"brand_id", "hashtag_id"}
                )
        })
public class BrandHashtags {

    /**
     * brandHashtags 테이블의 primary key 값을 저장합니다.
     * django 사용시 자동으로 관리되던 테이블이므로 id는 Long 타입을 가지고 있습니다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * brand 와 N:1로 관계를 가지기 위해 brand_id 라는 char(32) 형태의 uuid 를 저장하는 column 을 가지고 있습니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", columnDefinition = "CHAR(32)")
    private Brand brand;

    /**
     * hashtag 와 N:1로 관계를 가지기 위해 hashtag_id 라는 hashtag 의 name 을 저장하는 varchar(150) 형태의 column 을 가지고 있습니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id", columnDefinition = "VARCHAR(150)")
    private Hashtag hashtag;

}
