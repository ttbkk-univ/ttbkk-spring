package com.ttbkk.api.domain.hashtag;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "hashtag")
public class HashTag {

    @Id
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public HashTag(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }

}
