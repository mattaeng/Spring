package com.mattaeng.mattaengapi.dto.user;

import java.util.UUID;

import lombok.Builder;

@Builder
public record CreateUserResponse(
	UUID id,
	String userId,
	String username,
	String phoneNumber
) {
}
