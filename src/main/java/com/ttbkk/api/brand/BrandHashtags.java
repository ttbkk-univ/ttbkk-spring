package com.ttbkk.api.brand;

import com.ttbkk.api.hashtag.Hashtag;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//Brand 와 HashTag 의 매핑테이블
//unique 키 (제약조건) 생성 (brand_id, hashtag_id)
//Brand, Hashtag 클래스와 N:1 연관관계를 맺고 있어 해당 Brand, Hashtag 객체 필드로 가지고 있음.
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
