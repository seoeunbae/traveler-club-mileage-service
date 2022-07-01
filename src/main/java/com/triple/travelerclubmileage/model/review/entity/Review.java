package com.triple.travelerclubmileage.model.review.entity;

import com.triple.travelerclubmileage.model.common.time.entity.BaseTimeEntity;
import com.triple.travelerclubmileage.model.photo.entity.Photo;
import com.triple.travelerclubmileage.model.place.entity.Place;
import com.triple.travelerclubmileage.model.user.entity.User;
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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "review_id")
    private String reviewId;
    @Lob
    @Column
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
    private List<Photo> photos = new ArrayList<>();
}
