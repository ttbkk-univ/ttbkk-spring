package com.ttbkk.api.hashtag;

import com.ttbkk.api.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

//Entity 클래스
@Getter
@NoArgsConstructor
@Entity
@Table(name = "hashtag")
public class Hashtag extends BaseTimeEntity{

    @Id
    @Column(columnDefinition = "VARCHAR(150)")
    private String name;

    public Hashtag(String name) {
        this.name = name;
    }

}
