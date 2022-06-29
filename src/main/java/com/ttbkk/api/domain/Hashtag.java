package com.ttbkk.api.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Hashtag {

    @Column(name = "name")
    private String name;
    @Column(name = "created_at")
    private Timestamp createdAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
