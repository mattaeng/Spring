package com.mattaeng.mattaengapi.dto.user;

import java.util.UUID;

import com.mattaeng.mattaengapi.domain.User;
import com.mattaeng.mattaengapi.security.CustomUserDetails;

import lombok.Builder;

@Builder
public record UserInfoResponse(
	UUID id,
	String userId,
	String username,
	String phoneNumber,
	Boolean isEnabled
) {
	public static UserInfoResponse from(CustomUserDetails userDetails) {
		return UserInfoResponse.builder()
			.id(userDetails.getId())
			.userId(userDetails.getUserId())
			.username(userDetails.getUsername())
			.phoneNumber(userDetails.getPhoneNumber())
			.isEnabled(userDetails.isEnabled())
			.build();
	}

	public static UserInfoResponse from(User user) {
		return UserInfoResponse.builder()
			.id(user.getId())
			.userId(user.getUserId())
			.username(user.getUsername())
			.phoneNumber(user.getPhoneNumber())
			.isEnabled(user.isEnabled())
			.build();
	}
}
