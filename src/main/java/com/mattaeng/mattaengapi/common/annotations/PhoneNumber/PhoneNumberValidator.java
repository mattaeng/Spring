package com.mattaeng.mattaengapi.common.annotations.PhoneNumber;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

	@Override
	public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
		return Pattern
			.compile("[0-9]{11}")
			.matcher(phoneNumber)
			.matches();
	}
}
