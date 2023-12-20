package com.mattaeng.mattaengapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mattaeng.mattaengapi.common.api.Api;
import com.mattaeng.mattaengapi.dto.user.CreateUserRequest;
import com.mattaeng.mattaengapi.dto.user.CreateUserResponse;
import com.mattaeng.mattaengapi.dto.user.MyInfoResponse;
import com.mattaeng.mattaengapi.security.CustomUserDetails;
import com.mattaeng.mattaengapi.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@Operation(summary = "회원가입 API")
	@PostMapping("/users")
	public Api<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
		return Api.ok(userService.createUser(createUserRequest));
	}

	@Operation(summary = "내 정보 가져오기")
	@GetMapping("/users/me")
	public Api<MyInfoResponse> getMyInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {
		return Api.ok(userService.getMyInfo(userDetails));
	}
}
