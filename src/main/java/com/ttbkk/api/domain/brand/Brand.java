package com.ttbkk.api.domain.brand;

import com.ttbkk.api.BaseTimeEntity;
import com.ttbkk.api.domain.place.Place;
import com.ttbkk.api.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "brand")
public class Brand extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(150)")
    private String name;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id")
    private User createdByUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by_id")
    private User updatedByUser;

    @OneToMany(mappedBy = "brand")
    private List<Place> placeList = new ArrayList<>();
}
