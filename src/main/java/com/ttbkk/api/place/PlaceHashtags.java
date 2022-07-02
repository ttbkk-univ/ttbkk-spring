package com.ttbkk.api.place;

import com.ttbkk.api.hashtag.Hashtag;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//Brand 와 HashTag 의 매핑테이블
//unique 키 (제약조건) 생성 (place_id, hashtag_id)
//Place, Hashtag 클래스와 N:1 연관관계를 맺고 있어 해당 Place, Hashtag 객체 필드로 가지고 있음.
@Getter
@NoArgsConstructor
@Entity
@Table(name = "place_hashtags",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "place_hashtags_place_id_hashtag_id_3e5ba0f0_uniq",
                        columnNames = {"place_id", "hashtag_id"}
                )
        })
public class PlaceHashtags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", columnDefinition = "CHAR(32)")
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id", columnDefinition = "VARCHAR(150)")
    private Hashtag hashtag;

}
