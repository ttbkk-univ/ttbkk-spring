package com.ttbkk.api.place;

import com.ttbkk.api.hashtag.Hashtag;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//Place 와 Hashtag 의 N:M 관계를 구현하기 위한 중간 테이블입니다.
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
