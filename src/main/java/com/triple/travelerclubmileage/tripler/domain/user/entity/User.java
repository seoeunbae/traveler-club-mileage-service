package com.triple.travelerclubmileage.tripler.domain.user.entity;

import com.triple.travelerclubmileage.tripler.common.time.entity.BaseTimeEntity;
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
@Table(name = "`user`") //H2는 PK, UUID에 대해서 인덱스가 자동생성됨.
@DynamicInsert
@DynamicUpdate
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "user_id")
    @Type(type = "uuid-char")
    private UUID id = UUID.randomUUID();

    @Column(length = 20) //우리나라 가장 긴 이름:17자
    private String username;

    @Column(length = 20, unique = true)
    private String nickname;

    @Column(length = 65)
    private String password;

    @Column(length = 15)
    private String phoneNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column
    private Integer mileage;

    @Column
    private Boolean isEnabled;

    public enum UserRole{
        USER_ROLE, ADMIN_ROLE
    }
}
