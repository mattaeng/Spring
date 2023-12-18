package com.mattaeng.mattaengapi.domain;

import com.mattaeng.mattaengapi.dto.user.CreateUserResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Builder
public class User {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String userId;

    private String password;

    private String username;

    private String phoneNumber;

    public User(UUID id, String userId, String password, String username, String phoneNumber) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.phoneNumber = phoneNumber;
    }

    public User() {
    }

    public void setId(UUID id) {
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

    public CreateUserResponse toCreateUserResponse() {
        return CreateUserResponse.builder()
            .id(this.id)
            .userId(this.userId)
            .username(this.username)
            .phoneNumber(this.phoneNumber)
            .build();
    }
}
