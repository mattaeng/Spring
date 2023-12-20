package com.mattaeng.mattaengapi.dto.user;

import java.util.UUID;

import com.mattaeng.mattaengapi.security.CustomUserDetails;

import lombok.Builder;

@Builder
public record MyInfoResponse(
	UUID id,
	String userId,
	String username,
	String phoneNumber,
	Boolean isEnabled
) {
	public static MyInfoResponse from(CustomUserDetails userDetails) {
		return MyInfoResponse.builder()
			.id(userDetails.getId())
			.userId(userDetails.getUserId())
			.username(userDetails.getUsername())
			.phoneNumber(userDetails.getPhoneNumber())
			.isEnabled(userDetails.isEnabled())
			.build();
	}
}
