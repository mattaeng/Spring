package com.mattaeng.mattaengapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mattaeng.mattaengapi.common.api.Api;
import com.mattaeng.mattaengapi.dto.auth.LoginRequest;
import com.mattaeng.mattaengapi.dto.auth.LoginResponse;
import com.mattaeng.mattaengapi.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@Operation(summary = "로그인")
	@PostMapping("/login")
	public Api<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		return Api.ok(authService.login(loginRequest));
	}
}
