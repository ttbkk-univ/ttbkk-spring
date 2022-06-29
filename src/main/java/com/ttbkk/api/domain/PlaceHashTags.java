package com.ttbkk.api.domain;

import com.ttbkk.api.domain.hashtag.HashTag;
import com.ttbkk.api.domain.place.Place;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "place_hashtags",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "place_hashtags_place_id_hashtag_id_3e5ba0f0_uniq",
                        columnNames = {"place_id", "hashTag"}
                )
        })
public class PlaceHashTags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", columnDefinition = "CHAR(32)")
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashTag_id", columnDefinition = "VARCHAR(150)")
    private HashTag hashTag;
}
