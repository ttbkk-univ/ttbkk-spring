package com.ttbkk.api.brand;

import com.ttbkk.api.hashtag.Hashtag;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//Brand와 Hashtag의 N:M 관계를 구현하기 위한 중간 테이블입니다.
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", columnDefinition = "CHAR(32)")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id", columnDefinition = "VARCHAR(150)")
    private Hashtag hashtag;

}
