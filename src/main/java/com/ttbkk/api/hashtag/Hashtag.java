package com.ttbkk.api.hashtag;

import com.ttbkk.api.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

//Entity 클래스
@Getter
@NoArgsConstructor
@Entity
@Table(name = "hashtag")
@EntityListeners(AuditingEntityListener.class)
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
