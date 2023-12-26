package com.mattaeng.mattaengapi.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mattaeng.mattaengapi.common.api.Api;
import com.mattaeng.mattaengapi.dto.user.CreateUserRequest;
import com.mattaeng.mattaengapi.dto.user.UpdateUserInfoRequest;
import com.mattaeng.mattaengapi.dto.user.UpdateUserPasswordRequest;
import com.mattaeng.mattaengapi.dto.user.UserInfoResponse;
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

	@Operation(summary = "회원가입")
	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	public Api<UserInfoResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
		return Api.ok(userService.createUser(createUserRequest));
	}

	@Operation(summary = "내 정보 가져오기")
	@GetMapping("/users/me")
	public Api<UserInfoResponse> getMyInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {
		return Api.ok(userService.getMyInfo(userDetails));
	}

	@Operation(summary = "유저 정보 조회")
	@GetMapping("/users/{id}")
	public Api<UserInfoResponse> getUserInfo(
		@AuthenticationPrincipal CustomUserDetails userDetails,
		@PathVariable UUID id
	) {
		return Api.ok(userService.getUserInfo(userDetails, id));
	}

	@Operation(summary = "유저 정보 수정")
	@PutMapping("/users/info")
	public Api<UserInfoResponse> updateUserInfo(
		@AuthenticationPrincipal CustomUserDetails userDetails,
		@Valid @RequestBody UpdateUserInfoRequest updateUserInfoRequest
	) {
		return Api.ok(userService.updateUserInfo(userDetails, updateUserInfoRequest));
	}

	@Operation(summary = "비밀번호 변경")
	@PutMapping("/users/password")
	public Api<UserInfoResponse> updateUserPassword(
		@AuthenticationPrincipal CustomUserDetails userDetails,
		@RequestBody UpdateUserPasswordRequest updateUserPasswordRequest
	) {
		return Api.ok(userService.updateUserPassword(userDetails, updateUserPasswordRequest));
	}
}
