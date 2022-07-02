package com.ttbkk.api.domain.place;

import com.ttbkk.api.BaseTimeEntity;
import com.ttbkk.api.domain.brand.Brand;
import com.ttbkk.api.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

//Entity 클래스 . BaseTimeEntity 클래스를 상속 받아 자동 시간 생성.
//User, Brand 클래스와 N:1 연관관계를 맺고 있어 해당 User, Brand 객체 필드로 가지고 있음.
@Getter
@NoArgsConstructor
@Entity
@Table(name = "place")
public class Place extends BaseTimeEntity {

    @Id
    @Column(columnDefinition = "CHAR(32)")
    private String id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(100)")
    private String name;

//  위치의 경우 정확한 값을 요구하기 때문에 BigDecimal 타입 사용
    @NotNull
    @Column(columnDefinition = "DECIMAL(15,13)")
    private BigDecimal latitude;

    @NotNull
    @Column(columnDefinition = "DECIMAL(15,12)")
    private BigDecimal longitude;

    @NotNull
    @Column(name = "is_deleted", columnDefinition = "TINYINT(1)")
    private boolean isDeleted;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @Column(columnDefinition = "VARCHAR(100)")
    private String telephone;

    @Column(columnDefinition = "VARCHAR(100)")
    private String address;

    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "DATETIME(6)")
    private LocalDateTime updatedAt;

    //user, brand 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id", columnDefinition = "CHAR(32)")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by_id", columnDefinition = "CHAR(32)")
    private User updatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", columnDefinition = "CHAR(32)")
    private Brand brand;

    @Builder
    public Place(String name, BigDecimal latitude, BigDecimal longitude, boolean isDeleted, String description, String telephone, String address) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isDeleted = isDeleted;
        this.description = description;
        this.telephone = telephone;
        this.address = address;
    }

}
