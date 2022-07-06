package com.triple.travelerclubmileage.tripler.domain.review.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.triple.travelerclubmileage.tripler.common.time.entity.BaseTimeEntity;
import com.triple.travelerclubmileage.tripler.domain.photo.entity.Photo;
import com.triple.travelerclubmileage.tripler.domain.place.entity.Place;
import com.triple.travelerclubmileage.tripler.domain.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity

@Getter
@Setter
@Table(name = "review")
@DynamicInsert
@DynamicUpdate
public class Review extends BaseTimeEntity implements Serializable {
    @Id
    @Column(name = "review_id")
    @Type(type = "uuid-char")
    private UUID id = UUID.randomUUID();
    @Column(nullable = true, columnDefinition = "TEXT")
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", referencedColumnName = "place_id")
    private Place place;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
    @Column
    private Boolean isEnabled;
    @Column
    private Boolean isFirst;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "review", cascade = CascadeType.REMOVE)
    @Column(nullable = true)
    @JsonManagedReference
    private List<Photo> photos = new ArrayList<>();
}
