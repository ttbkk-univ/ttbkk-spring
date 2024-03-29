package com.ttbkk.api.user;

import com.ttbkk.api.common.entity.BaseTimeEntity;
import com.ttbkk.api.brand.Brand;
import com.ttbkk.api.place.Place;
import com.ttbkk.api.review.Review;
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
    @Column(columnDefinition = "DEFAULT 'USER'")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @NotNull
    @Column(name = "social_id", columnDefinition = "VARCHAR(50)", unique = true)
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private final List<Review> reviews = new ArrayList<>();

    /**
     * User 생성자.
     * @param socialId 로그인 서비스에서 사용중인 식별자. 예) email
     * @param socialType 로그인 서비스 이름
     * @param role 유저의 역할
     */
    @Builder
    public User(String socialId, String socialType, UserRole role) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.socialId = socialId;
        this.socialType = socialType;
        this.role = role;
    }

}
