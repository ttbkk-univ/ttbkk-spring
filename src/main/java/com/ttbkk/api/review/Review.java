package com.ttbkk.api.review;

import com.ttbkk.api.common.entity.BaseTimeEntity;
import com.ttbkk.api.place.Place;
import com.ttbkk.api.user.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "TINYINT")
    private int score;

    @Column(columnDefinition = "TEXT")
    private String comment;

    //user, place 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "CHAR(32)")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", columnDefinition = "CHAR(32)")
    private Place place;

    /**
     * Review 생성자
     * @param score 점수
     * @param comment 리뷰 내용
     */
    @Builder
    public Review(int score, String comment) {
        this.score = score;
        this.comment = comment;
    }
}
