package com.mattaeng.mattaengapi.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.mattaeng.mattaengapi.dto.user.UpdatePhoneNumberRequest;
import com.mattaeng.mattaengapi.dto.user.UpdateUserPasswordRequest;
import com.mattaeng.mattaengapi.dto.user.UpdateUsernameRequest;
import com.mattaeng.mattaengapi.dto.user.UserInfoResponse;
import com.mattaeng.mattaengapi.security.CustomUserDetails;
import com.mattaeng.mattaengapi.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@Operation(summary = "회원가입")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Api<UserInfoResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
		return Api.ok(userService.createUser(createUserRequest));
	}

	@Operation(summary = "내 정보 가져오기")
	@GetMapping("/me")
	public Api<UserInfoResponse> getMyInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {
		return Api.ok(userService.getMyInfo(userDetails));
	}

	@Operation(summary = "유저 정보 조회")
	@GetMapping("/{id}")
	public Api<UserInfoResponse> getUserInfo(
		@AuthenticationPrincipal CustomUserDetails userDetails,
		@PathVariable UUID id
	) {
		return Api.ok(userService.getUserInfo(userDetails, id));
	}

	@Operation(summary = "유저 이름 변경")
	@PutMapping("/username")
	public Api<UserInfoResponse> updateUsername(
		@AuthenticationPrincipal CustomUserDetails userDetails,
		@Valid @RequestBody UpdateUsernameRequest updateUsernameRequest
	) {
		return Api.ok(userService.updateUsername(userDetails, updateUsernameRequest));
	}

	@Operation(summary = "유저 휴대폰 번호 변경")
	@PutMapping("/phone-number")
	public Api<UserInfoResponse> updateUserPhoneNumber(
		@AuthenticationPrincipal CustomUserDetails userDetails,
		@Valid @RequestBody UpdatePhoneNumberRequest updatePhoneNumberRequest
	) {
		return Api.ok(userService.updatePhoneNumber(userDetails, updatePhoneNumberRequest));
	}

	@Operation(summary = "비밀번호 변경")
	@PutMapping("/password")
	public Api<UserInfoResponse> updateUserPassword(
		@AuthenticationPrincipal CustomUserDetails userDetails,
		@RequestBody UpdateUserPasswordRequest updateUserPasswordRequest
	) {
		return Api.ok(userService.updateUserPassword(userDetails, updateUserPasswordRequest));
	}

	@Operation(summary = "유저 삭제")
	@DeleteMapping
	public void deleteUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
		userService.deleteUser(userDetails);
	}
}
