package com.ttbkk.api.domain.hashtag;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

//Entity 클래스
@Getter
@NoArgsConstructor
@Entity
@Table(name = "hashtag")
public class Hashtag {

    @Id
    @Column(columnDefinition = "VARCHAR(150)")
    private String name;

    @CreatedDate
    @Column(name = "created_at", columnDefinition = "DATETIME(6)")
    private LocalDateTime createdAt;

    public Hashtag(String name) {
        this.name = name;
    }

}
