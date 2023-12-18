package com.mattaeng.mattaengapi.dto.auth;

import lombok.Builder;

@Builder
public record LoginResponse(
	String accessToken
) {
}
