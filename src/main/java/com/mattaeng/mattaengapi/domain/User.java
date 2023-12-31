package com.mattaeng.mattaengapi.domain;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(generator = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	private String userId;

	private String password;

	private String username;

	private String phoneNumber;

	private Boolean isAccountNonLocked; // TODO: 약관 동의. 약관 동의 안 하면 회원가입이 안되야하는 거 아닌가?

	private Boolean isEnabled; // TODO: Delete 시, false

	@Builder
	private User(UUID id, String userId, String password, String username, String phoneNumber,
		Boolean isAccountNonLocked, Boolean isEnabled) {
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.username = username;
		this.phoneNumber = phoneNumber;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isEnabled = isEnabled;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEnabled(Boolean enabled) {
		isEnabled = enabled;
	}

	public Boolean isEnabled() {
		return this.isEnabled;
	}
}
