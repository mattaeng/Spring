package com.mattaeng.mattaengapi.dto.user;

import com.mattaeng.mattaengapi.common.annotations.Password.Password;
import com.mattaeng.mattaengapi.domain.User;
import com.mattaeng.mattaengapi.security.BCryptConfig;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateUserRequest(

	@NotBlank(message = "이메일을 입력해주세요")
	@Email(message = "이메일 형식으로 입력해주세요")
	String userId,

	@Password
	@NotBlank(message = "비밀번호를 입력해주세요")
	String password,

	@NotBlank(message = "이름을 입력해주세요")
	String username,

	@NotBlank(message = "전화번호를 입력해주세요")
	@Pattern(regexp = "[0-9]{11}", message = "잘못된 전화번호 형식입니다.") // TODO: +82 XXX-XXXX-XXXX. 프론트쪽에서 편한 거로 진행
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
