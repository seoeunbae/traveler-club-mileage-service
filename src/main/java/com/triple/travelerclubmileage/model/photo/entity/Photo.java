package com.triple.travelerclubmileage.model.photo.entity;

import com.triple.travelerclubmileage.model.common.time.entity.BaseTimeEntity;
import com.triple.travelerclubmileage.model.review.entity.Review;
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
@Table(name = "photo")
@DynamicUpdate
@DynamicInsert
public class Photo extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "photo_id")
    private UUID PhotoId;
    @Column
    private String resource;
    @Column
    private PhotoType type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;
    @Column
    private Boolean isEnabled;
    private enum PhotoType{
        REVIEW, PLACE, USER, AIRPLANE
    }
}
