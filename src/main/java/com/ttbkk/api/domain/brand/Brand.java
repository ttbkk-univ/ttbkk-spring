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
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "brand")
public class Brand extends BaseTimeEntity {
    @Id
    @Column(columnDefinition = "CHAR(32)")
    private UUID id;

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
}
