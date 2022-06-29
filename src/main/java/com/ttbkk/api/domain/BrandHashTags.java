package com.ttbkk.api.domain;

import com.ttbkk.api.domain.brand.Brand;
import com.ttbkk.api.domain.hashtag.Hashtag;
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
                        columnNames = {"brand_id", "hashtag_id"}
                )
        })
public class BrandHashTags {
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
