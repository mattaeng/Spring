package com.mattaeng.mattaengapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String password;
    private String username;
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
