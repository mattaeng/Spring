package com.mattaeng.mattaengapi.dto.user;

import com.mattaeng.mattaengapi.domain.User;
import com.mattaeng.mattaengapi.security.BCryptConfig;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateUserRequest {

	@NotBlank(message = "이메일을 입력해주세요")
	@Email(message = "이메일 형식으로 입력해주세요")
	private String userId;

	@NotBlank(message = "비밀번호를 입력해주세요")
	@Pattern(
		regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()])[a-zA-Z0-9!@#$%^&*()]{8,}$",
		message = "잘못된 패스워드입니다. 공백을 제외한 소문자, 대문자, 특수문자, 숫자를 포함하여 8글자 이상 입력해주세요"
	)
	private String password;

	@NotBlank(message = "이름을 입력해주세요")
	private String username;

	@NotBlank(message = "전화번호를 입력해주세요")
	@Pattern(regexp = "[0-9]{11}", message = "잘못된 전화번호 형식입니다.") // TODO: +82 XXX-XXXX-XXXX. 프론트쪽에서 편한 거로 진행
	private String phoneNumber;

	public User toUser() {
		BCryptConfig bCryptConfig = new BCryptConfig();
		return User.builder()
			.userId(this.userId)
			.password(bCryptConfig.passwordEncoder().encode(this.password))
			.username(this.username)
			.phoneNumber(this.phoneNumber)
			.build();
	}
}
