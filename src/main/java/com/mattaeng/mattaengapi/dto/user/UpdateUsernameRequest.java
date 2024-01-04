package com.mattaeng.mattaengapi.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UpdateUsernameRequest(

	@NotBlank(message = "이름을 입력해주세요")
	String username
) {
}