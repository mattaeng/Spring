package com.mattaeng.mattaengapi.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UpdateUserPasswordRequest(
	@NotBlank(message = "기존 비밀번호를 입력해주세요")
	@Pattern(
		regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()])[a-zA-Z0-9!@#$%^&*()]{8,}$",
		message = "잘못된 패스워드입니다. 공백을 제외한 소문자, 대문자, 특수문자, 숫자를 포함하여 8글자 이상 입력해주세요"
	)
	String oldPassword,

	@NotBlank(message = "새로운 비밀번호를 입력해주세요")
	@Pattern(
		regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()])[a-zA-Z0-9!@#$%^&*()]{8,}$",
		message = "잘못된 패스워드입니다. 공백을 제외한 소문자, 대문자, 특수문자, 숫자를 포함하여 8글자 이상 입력해주세요"
	)
	String newPassword
) {
}
