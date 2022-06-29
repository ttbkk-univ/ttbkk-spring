package com.ttbkk.api.domain.place;

import com.ttbkk.api.BaseTimeEntity;
import com.ttbkk.api.domain.brand.Brand;
import com.ttbkk.api.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "place")
public class Place extends BaseTimeEntity {

    @Id
    @Column(columnDefinition = "CHAR(32)")
    private UUID id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(100)")
    private String name;

//  DECIMAL(20, 5)라고 정의하면 정수부를 15자리, 소수부를 5
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

    //다 설정해 줘야 하나?? 라는 의문
    @Column(columnDefinition = "VARCHAR(100)")
    private String address;

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
}
