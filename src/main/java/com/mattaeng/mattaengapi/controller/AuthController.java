package com.mattaeng.mattaengapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mattaeng.mattaengapi.common.api.Api;
import com.mattaeng.mattaengapi.dto.auth.LoginRequest;
import com.mattaeng.mattaengapi.dto.auth.LoginResponse;
import com.mattaeng.mattaengapi.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public Api<LoginResponse> login(@RequestBody LoginRequest request) {
		return Api.ok(authService.login(request));
	}
}
