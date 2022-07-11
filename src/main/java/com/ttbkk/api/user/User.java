package com.ttbkk.api.user;

import com.ttbkk.api.common.entity.BaseTimeEntity;
import com.ttbkk.api.brand.Brand;
import com.ttbkk.api.place.Place;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//Entity 클래스 . BaseTimeEntity 클래스를 상속 받아 자동 시간 생성.
//생성한 Brand,Place & 업데이트한 Brand,Place 들을 조회(읽기) 할 수 있다.
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseTimeEntity {

    @Id
    @Column(columnDefinition = "CHAR(32)")
    private String id;

    @NotNull
    @Column(name = "social_id", columnDefinition = "VARCHAR(50)")
    private String socialId;

    @NotNull
    @Column(name = "social_type", columnDefinition = "VARCHAR(20)")
    private String socialType;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private final List<Brand> createBrands = new ArrayList<>();

    @OneToMany(mappedBy = "updatedBy", cascade = CascadeType.ALL)
    private final List<Brand> updateBrands = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private final List<Place> createPlaces = new ArrayList<>();

    @OneToMany(mappedBy = "updatedBy", cascade = CascadeType.ALL)
    private final List<Place> updatePlaces = new ArrayList<>();

    /**
     * User 생성자.
     * @param socialId 로그인 서비스에서 사용중인 식별자. 예) email
     * @param socialType 로그인 서비스 이름
     */
    @Builder
    public User(String socialId, String socialType) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.socialId = socialId;
        this.socialType = socialType;
    }

}
