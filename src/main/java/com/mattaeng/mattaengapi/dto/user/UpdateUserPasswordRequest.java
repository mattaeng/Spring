package com.mattaeng.mattaengapi.dto.user;

import com.mattaeng.mattaengapi.common.annotations.Password.Password;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserPasswordRequest(
	@Password
	String oldPassword,

	@Password
	@NotBlank(message = "새로운 비밀번호를 입력해주세요")
	String newPassword
) {
}
