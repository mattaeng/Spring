package com.mattaeng.mattaengapi.entity;

import com.mattaeng.mattaengapi.common.auditing.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String userId;

    @NonNull
    private String password;

    @NonNull
    private String username;

    @NonNull
    private String phoneNumber;

    public User(Long id, String userId, String password, String username, String phoneNumber) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.phoneNumber = phoneNumber;
    }

    public User() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
