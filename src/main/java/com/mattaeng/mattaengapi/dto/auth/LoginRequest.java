package com.mattaeng.mattaengapi.dto.auth;

public record LoginRequest(
	String userId,
	String password
) {
}