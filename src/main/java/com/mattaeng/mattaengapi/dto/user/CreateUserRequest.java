package com.mattaeng.mattaengapi.dto.user;

import com.mattaeng.mattaengapi.common.annotations.Password.Password;
import com.mattaeng.mattaengapi.common.annotations.PhoneNumber.PhoneNumber;
import com.mattaeng.mattaengapi.domain.User;
import com.mattaeng.mattaengapi.security.BCryptConfig;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(

	@NotBlank(message = "이메일을 입력해주세요")
	@Email(message = "이메일 형식으로 입력해주세요")
	String userId,

	@Password
	@NotBlank(message = "비밀번호를 입력해주세요")
	String password,

	@NotBlank(message = "이름을 입력해주세요")
	String username,

	@PhoneNumber
	@NotBlank(message = "전화번호를 입력해주세요")
	String phoneNumber
) {
	public User toEntity() {
		BCryptConfig bCryptConfig = new BCryptConfig();
		return User.builder()
			.userId(this.userId)
			.password(bCryptConfig.passwordEncoder().encode(this.password))
			.username(this.username)
			.phoneNumber(this.phoneNumber)
			.isAccountNonLocked(true)
			.isEnabled(true)
			.build();
	}
}
