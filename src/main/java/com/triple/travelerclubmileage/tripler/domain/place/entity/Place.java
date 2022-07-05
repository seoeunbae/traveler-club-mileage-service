package com.triple.travelerclubmileage.tripler.domain.place.entity;

import com.triple.travelerclubmileage.tripler.domain.common.time.entity.BaseTimeEntity;
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
@Table(name = "place")
@DynamicUpdate
@DynamicInsert
public class Place extends BaseTimeEntity implements Serializable {
    @Id
    @Column(name = "place_id")
    @Type(type = "uuid-char")
    private UUID id = UUID.randomUUID();
    @Column(length = 100)
    private String name;
    @Column(nullable = true, columnDefinition = "TEXT")
    private String description;
    @Column(length = 90) //(세계에서 가장 긴 지명: 85자)
    private String location;
    @Column
    @Enumerated(EnumType.STRING)
    private PlaceType type;
    @Column
    private Boolean isEnabled;
    public enum PlaceType{
        FOOD, SIGHTSEEING, TOUR
    }
}
