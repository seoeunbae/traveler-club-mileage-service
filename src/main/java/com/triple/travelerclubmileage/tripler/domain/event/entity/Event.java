package com.triple.travelerclubmileage.tripler.domain.event.entity;

import com.triple.travelerclubmileage.tripler.common.time.entity.BaseTimeEntity;
import com.triple.travelerclubmileage.tripler.domain.user.entity.User;
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
@Table(name = "event")
@DynamicUpdate
@DynamicInsert
public class Event extends BaseTimeEntity {

    @Id
    @Column(name = "event_id")
    @Type(type = "uuid-char")
    private UUID id = UUID.randomUUID();

    @Column
    @Enumerated(EnumType.STRING)
    private EventTargetType eventTargetType;

    @Column
    @Type(type = "uuid-char")
    private UUID eventTargetId;

    @Column
    @Enumerated(EnumType.STRING)
    private EventActionType eventActionType;

    @Column
    private Boolean isEnabled;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String content;

    public enum EventTargetType{
        REVIEW, REPORT, PLACE
    }

    public enum EventActionType{
        ADD, MOD, DELETE
    }
}
