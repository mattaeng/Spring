package com.mattaeng.mattaengapi.dto.user;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateUserResponse {

    private String userId;
    private String username;
    private String phoneNumber;
}
