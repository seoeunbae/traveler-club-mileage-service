package com.triple.travelerclubmileage.model.review.entity;

import com.triple.travelerclubmileage.model.common.time.entity.BaseTimeEntity;
import com.triple.travelerclubmileage.model.place.entity.Place;
import com.triple.travelerclubmileage.model.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
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
    private UUID reviewId;
    @Lob
    @Column
    private String content;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private Boolean isEnabled;
}
