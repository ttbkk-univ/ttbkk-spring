package com.ttbkk.api.domain.user;

import com.ttbkk.api.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseTimeEntity {

    @Id
    @Column(columnDefinition = "CHAR(32)")
    private UUID id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(50)")
    private String nickname;

    @NotNull
    @Column(name = "social_id", columnDefinition = "VARCHAR(50)")
    private String socialId;

    @NotNull
    @Column(name = "social_type", columnDefinition = "VARCHAR(20)")
    private String socialType;


//    @OneToMany(mappedBy = "createdByUser", cascade = CascadeType.ALL)
//    private List<Brand> createBrandList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "updatedByUser", cascade = CascadeType.ALL)
//    private List<Brand> updateBrandList = new ArrayList<>();

    @Builder
    public User(String nickname, String socialId, String socialType) {
        this.id = UUID.randomUUID();
        this.nickname = nickname;
        this.socialId = socialId;
        this.socialType = socialType;
    }
}
