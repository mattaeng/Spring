package com.mattaeng.mattaengapi.common.annotations.PhoneNumber;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
@Documented
public @interface PhoneNumber {

	String message() default "잘못된 전화번호 형식입니다.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
