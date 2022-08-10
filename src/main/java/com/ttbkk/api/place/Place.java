package com.ttbkk.api.place;

import com.ttbkk.api.common.entity.BaseTimeEntity;
import com.ttbkk.api.brand.Brand;
import com.ttbkk.api.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Entity 클래스 . BaseTimeEntity 클래스를 상속 받아 자동 시간 생성.
 * User, Brand 클래스와 N:1 연관관계를 맺고 있어 해당 User, Brand 객체 필드로 가지고 있음.
 */
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

//  위치의 경우 정확한 값을 요구하기 때문에 BigDecimal 타입 사용.
    @NotNull
    @Column(columnDefinition = "DECIMAL(15,13)")
    private BigDecimal latitude;

    @NotNull
    @Column(columnDefinition = "DECIMAL(15,12)")
    private BigDecimal longitude;

    @NotNull
    @Column(name = "is_deleted", columnDefinition = "TINYINT(1)")
    private boolean isDeleted = false;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @Column(columnDefinition = "VARCHAR(100)")
    private String telephone;

    @Column(columnDefinition = "VARCHAR(100)")
    private String address;

    /**
     * isDeleted field setter method.
     * @param isDeleted
     */
    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

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

    /**
     * Place 생성자.
     * @param name 장소 이름
     * @param latitude 위도
     * @param longitude 경도
     * @param description 설명
     * @param telephone 전화번호
     * @param address 주소
     */
    @Builder
    public Place(String name, BigDecimal latitude, BigDecimal longitude, String description, String telephone, String address) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.telephone = telephone;
        this.address = address;
    }

}
