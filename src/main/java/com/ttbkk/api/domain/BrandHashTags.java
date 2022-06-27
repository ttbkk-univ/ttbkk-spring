package com.ttbkk.api.domain;

import com.ttbkk.api.domain.brand.Brand;
import com.ttbkk.api.domain.hashtag.HashTag;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "brand_hashtags",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "brand_hashtags_brand_id_hashtag_id_696de634_uniq",
                        columnNames = {"brand_id", "hashTag"}
                )
        })
public class BrandHashTags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashTag")
    private HashTag hashTag;

}
