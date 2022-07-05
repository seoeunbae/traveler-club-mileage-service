package com.triple.travelerclubmileage.model.photo.entity;

import com.triple.travelerclubmileage.model.common.time.entity.BaseTimeEntity;
import com.triple.travelerclubmileage.model.review.entity.Review;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

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
    @Id
    @Column(name = "photo_id")
    @Type(type = "uuid-char")
    private UUID id = UUID.randomUUID();
    @Column
    private String resource;
    @Column
    @Enumerated(EnumType.STRING)
    private PhotoType type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", referencedColumnName = "review_id")
    private Review review;
    @Column
    private Boolean isEnabled;
    public enum PhotoType{
        REVIEW, PLACE, USER, AIRPLANE
    }
}
