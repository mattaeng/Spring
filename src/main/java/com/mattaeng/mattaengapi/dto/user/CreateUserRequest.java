package com.mattaeng.mattaengapi.dto.user;

import com.mattaeng.mattaengapi.entity.User;
import com.mattaeng.mattaengapi.security.BCryptConfig;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateUserRequest {

    private String userId;
    private String password;
    private String username;
    private String phoneNumber;

    public User toUser() {
        BCryptConfig bCryptConfig = new BCryptConfig();
        return User.builder()
                .userId(this.userId)
                .password(bCryptConfig.passwordEncoder().encode(this.password))
                .username(this.username)
                .phoneNumber(this.phoneNumber)
                .build();
    }
}
