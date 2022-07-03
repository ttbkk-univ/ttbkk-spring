package com.ttbkk.api.user;

import com.ttbkk.api.BaseTimeEntity;
import com.ttbkk.api.brand.Brand;
import com.ttbkk.api.place.Place;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//Entity 클래스 . BaseTimeEntity 클래스를 상속 받아 자동 시간 생성.
//생성한 Brand,Place & 업데이트한 Brand,Place 들을 조회(읽기) 할 수 있다.
@Getter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseTimeEntity {

    @Id
    @Column(columnDefinition = "CHAR(32)")
    private String id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(50)")
    private String nickname;

    @NotNull
    @Column(name = "social_id", columnDefinition = "VARCHAR(50)")
    private String socialId;

    @NotNull
    @Column(name = "social_type", columnDefinition = "VARCHAR(20)")
    private String socialType;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Brand> createBrands = new ArrayList<>();

    @OneToMany(mappedBy = "updatedBy", cascade = CascadeType.ALL)
    private List<Brand> updateBrands = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Place> createPlaces = new ArrayList<>();

    @OneToMany(mappedBy = "updatedBy", cascade = CascadeType.ALL)
    private List<Place> updatePlaces = new ArrayList<>();

    @Builder
    public User(String nickname, String socialId, String socialType) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.nickname = nickname;
        this.socialId = socialId;
        this.socialType = socialType;
    }

}
