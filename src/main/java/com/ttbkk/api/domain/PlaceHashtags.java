package com.ttbkk.api.domain;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
public class PlaceHashtags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @Column(name = "place_id")
    private String placeId;
    @Column(name = "hashtag_id")
    private String hashtagId;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getHashtagId() {
        return hashtagId;
    }

    public void setHashtagId(String hashtagId) {
        this.hashtagId = hashtagId;
    }
}
