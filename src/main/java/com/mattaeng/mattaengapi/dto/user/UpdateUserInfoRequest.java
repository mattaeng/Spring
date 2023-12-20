package com.mattaeng.mattaengapi.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UpdateUserInfoRequest(
	@NotBlank(message = "이름을 입력해주세요")
	String username,

	@NotBlank(message = "전화번호를 입력해주세요")
	@Pattern(regexp = "[0-9]{11}", message = "잘못된 전화번호 형식입니다.") // TODO: +82 XXX-XXXX-XXXX. 프론트쪽에서 편한 거로 진행
	String phoneNumber
) {
}
