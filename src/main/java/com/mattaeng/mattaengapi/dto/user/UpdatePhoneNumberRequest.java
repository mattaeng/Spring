package com.mattaeng.mattaengapi.dto.user;

import com.mattaeng.mattaengapi.common.annotations.PhoneNumber.PhoneNumber;

import jakarta.validation.constraints.NotBlank;

public record UpdatePhoneNumberRequest(

	@PhoneNumber
	@NotBlank(message = "전화번호를 입력해주세요")
	String phoneNumber
) {
}
