package com.triple.travelerclubmileage.model.event.entity;

import com.triple.travelerclubmileage.model.common.time.entity.BaseTimeEntity;
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
@Table(name = "event")
@DynamicUpdate
@DynamicInsert
public class Event extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "event_id")
    private UUID eventId;
    @Column
    private EventTargetType eventTargetType;
    @Column
    private Long eventTargetId;
    @Column
    private EventActionType eventActionType;
    @Column
    private Boolean isEnabled;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    public enum EventTargetType{
        REVIEW, REPORT, PLACE
    }
    public enum EventActionType{
        ADD, MOD, DELETE
    }
}
