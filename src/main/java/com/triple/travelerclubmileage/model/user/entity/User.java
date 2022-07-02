package com.triple.travelerclubmileage.model.user.entity;

import com.triple.travelerclubmileage.model.common.time.entity.BaseTimeEntity;
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
@Table(name = "`user`")
@DynamicInsert
@DynamicUpdate
public class User extends BaseTimeEntity implements Serializable {
    @Id
    @Column(name = "user_id")
    @Type(type = "uuid-char")
    private UUID id = UUID.randomUUID();
    @Column(length = 20) //우리나라 가장 긴 이름:17자
    private String username;
    @Column(length = 20)
    private String nickname;
    @Column(length = 65)
    private String password;
    @Column(length = 15)
    private String phoneNumber;
    @Column
    private UserRole role;
    @Column
    private Integer mileage;
    @Column
    private Boolean isEnabled;
    public enum UserRole{
        USER_ROLE, ADMIN_ROLE
    }
}
