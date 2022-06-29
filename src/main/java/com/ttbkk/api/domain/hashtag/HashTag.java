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
    @Column(columnDefinition = "VARCHAR(150)")
    private String name;

    @NotNull
    @Column(name = "created_at", columnDefinition = "DATETIME(6)")
    private LocalDateTime createdAt;

    public HashTag(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }

}
