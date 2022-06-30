package com.triple.travelerclubmileage.model.place.entity;

import com.triple.travelerclubmileage.model.common.time.entity.BaseTimeEntity;
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
@Table(name = "place")
@DynamicUpdate
@DynamicInsert
public class Place extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "place_id")
    private UUID placeId;
    @Column(length = 100)
    private String name;
    @Lob
    @Column
    private String description;
    @Column(length = 90) //(세계에서 가장 긴 지명: 85자)
    private String location;
    @Column
    private PlaceType type;
//    @Column
//    private Integer reviewCount;
    private enum PlaceType{
        FOOD, SIGHTSEEING, TOUR
    }
}
