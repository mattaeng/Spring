package com.mattaeng.mattaengapi.common.annotations.Password;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface Password {

	String message() default "잘못된 패스워드입니다. 공백을 제외한 소문자, 대문자, 특수문자, 숫자를 포함하여 8글자 이상 입력해주세요";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
