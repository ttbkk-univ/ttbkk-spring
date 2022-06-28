package com.ttbkk.api.domain.user;

import com.ttbkk.api.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    //int , char 형 을 써야하나? 왜 char 형으로 되어 있는지 ddl.
    // auto_increment 로 하는게 낫지 않나?
    private Long id;

    @NotNull
    private String nickname;

    @NotNull
    @Column(name = "social_id")
    private String socialId;

    @NotNull
    @Column(name = "social_type")
    private String socialType;

//    @OneToMany(mappedBy = "createdByUser", cascade = CascadeType.ALL)
//    private List<Brand> createBrandList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "updatedByUser", cascade = CascadeType.ALL)
//    private List<Brand> updateBrandList = new ArrayList<>();

    @Builder
    public User(Long id, String nickname, String socialId, String socialType) {
        this.id = id;
        this.nickname = nickname;
        this.socialId = socialId;
        this.socialType = socialType;
    }
}
