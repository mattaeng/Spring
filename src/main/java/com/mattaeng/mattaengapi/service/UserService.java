package com.mattaeng.mattaengapi.service;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.mattaeng.mattaengapi.common.error.AuthErrorCode;
import com.mattaeng.mattaengapi.common.error.UserErrorCode;
import com.mattaeng.mattaengapi.common.exception.ApiException;
import com.mattaeng.mattaengapi.domain.User;
import com.mattaeng.mattaengapi.dto.user.CreateUserRequest;
import com.mattaeng.mattaengapi.dto.user.UpdateUserInfoRequest;
import com.mattaeng.mattaengapi.dto.user.UpdateUserPasswordRequest;
import com.mattaeng.mattaengapi.dto.user.UserInfoResponse;
import com.mattaeng.mattaengapi.repository.UserRepository;
import com.mattaeng.mattaengapi.security.BCryptConfig;
import com.mattaeng.mattaengapi.security.CustomUserDetails;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

	private final UserRepository userRepository;
	private final BCryptConfig bCryptConfig;

	// TODO: (추가) 휴대폰 인증, 약관 동의
	public UserInfoResponse createUser(CreateUserRequest createUserRequest) {
		if (userRepository.existsByUserIdOrPhoneNumber(createUserRequest.userId(), createUserRequest.phoneNumber())) {
			throw new ApiException(UserErrorCode.ALREADY_EXISTS_USER);
		}
		User user = userRepository.save(createUserRequest.toEntity());
		return UserInfoResponse.from(user);
	}

	public UserInfoResponse getMyInfo(CustomUserDetails userDetails) {
		return UserInfoResponse.from(userDetails);
	}

	public UserInfoResponse getUserInfo(CustomUserDetails userDetails, UUID id) {
		// TODO: (고민) 나에게 유저를 조회할 권한이 있는가?
		return userRepository.getUserById(id)
			.map(UserInfoResponse::from)
			.orElseThrow(() -> new ApiException(UserErrorCode.NOT_EXISTS_ID));
	}

	public UserInfoResponse updateUserInfo(CustomUserDetails userDetails, UpdateUserInfoRequest updateUserInfoRequest) {
		User user = userDetails.user();
		BeanUtils.copyProperties(updateUserInfoRequest, user);
		return UserInfoResponse.from(userRepository.save(user));
	}

	public UserInfoResponse updateUserPassword(
		CustomUserDetails userDetails,
		UpdateUserPasswordRequest updateUserPasswordRequest
	) {
		if (!bCryptConfig.passwordEncoder()
			.matches(updateUserPasswordRequest.oldPassword(), userDetails.getPassword())) {
			throw new ApiException(AuthErrorCode.INVALID_PASSWORD);
		}
		User user = userDetails.user();
		String updatedPassword = bCryptConfig.passwordEncoder().encode(updateUserPasswordRequest.newPassword());
		user.setPassword(updatedPassword);
		return UserInfoResponse.from(userRepository.save(user));
	}
}
