package com.mattaeng.mattaengapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mattaeng.mattaengapi.common.api.Api;
import com.mattaeng.mattaengapi.dto.user.CreateUserRequest;
import com.mattaeng.mattaengapi.dto.user.CreateUserResponse;
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
}
