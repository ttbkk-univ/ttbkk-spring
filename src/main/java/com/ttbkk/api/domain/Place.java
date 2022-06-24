package com.ttbkk.api.domain;

import com.ttbkk.api.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "place")
public class Place extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

//  DECIMAL(20, 5)라고 정의하면 정수부를 15자리, 소수부를 5
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;

    @NotNull
    @Column(name = "is_deleted")
    private Byte isDeleted;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @Column
    private String telephone;

    //다 설정해 줘야 하나?? 라는 의문
    @Column(columnDefinition = "VARCHAR(100)")
    private String address;

    //user, brand 연관관계
    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdByUser;

    @ManyToOne
    @JoinColumn(name = "updated_by_id")
    private User updatedByUser;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}
