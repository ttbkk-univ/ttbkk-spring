package com.ttbkk.api.domain;

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
    @Column(name = "place_hashtags_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "hashTag")
    private HashTag hashTag;
}
