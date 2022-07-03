package com.ttbkk.api.brand;

import com.ttbkk.api.BaseTimeEntity;
import com.ttbkk.api.place.Place;
import com.ttbkk.api.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//Entity 클래스 . BaseTimeEntity 클래스를 상속 받아 자동 시간 생성.
//User 클래스와 N:1 연관관계를 맺고 있어 해당 User 객체를 필드로 가지고 있음.
//Place 클래스와 1:N 연관관계를 맺고 있고 해당 리스트 읽기 가능. - 해당 브랜드의 장소들 조회(검색) 할 수 있다.
@Getter
@NoArgsConstructor
@Entity
@Table(name = "brand")
public class Brand extends BaseTimeEntity {

    @Id
    @Column(columnDefinition = "CHAR(32)")
    private String id;    //UUID 타입의 기본키 매핑. 크기는 CHAR(32)

    @NotNull
    @Column(columnDefinition = "VARCHAR(150)")
    private String name;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id", columnDefinition = "CHAR(32)")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by_id", columnDefinition = "CHAR(32)")
    private User updatedBy;

    @OneToMany(mappedBy = "brand")
    private List<Place> places = new ArrayList<>();

    @Builder
    public Brand(String name, String description) {
        this.id = UUID.randomUUID().toString().replace("_", "");
        this.name = name;
        this.description = description;
    }

}
