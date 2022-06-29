package com.ttbkk.api.domain;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
public class BrandHashtags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Bigint 에서도 적용 되는지?
    private BigInteger id;

    @Column(name = "brand_id")
    private String brandId;

    @Column(name = "hashtag_id")
    private String hashtagId;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getHashtagId() {
        return hashtagId;
    }

    public void setHashtagId(String hashtagId) {
        this.hashtagId = hashtagId;
    }
}
