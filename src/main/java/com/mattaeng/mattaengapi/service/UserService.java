package com.mattaeng.mattaengapi.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mattaeng.mattaengapi.common.error.AuthErrorCode;
import com.mattaeng.mattaengapi.common.error.UserErrorCode;
import com.mattaeng.mattaengapi.common.exception.ApiException;
import com.mattaeng.mattaengapi.domain.User;
import com.mattaeng.mattaengapi.dto.user.CreateUserRequest;
import com.mattaeng.mattaengapi.dto.user.UpdatePhoneNumberRequest;
import com.mattaeng.mattaengapi.dto.user.UpdateUserPasswordRequest;
import com.mattaeng.mattaengapi.dto.user.UpdateUsernameRequest;
import com.mattaeng.mattaengapi.dto.user.UserInfoResponse;
import com.mattaeng.mattaengapi.repository.UserRepository;
import com.mattaeng.mattaengapi.security.BCryptConfig;
import com.mattaeng.mattaengapi.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

	private final UserRepository userRepository;
	private final BCryptConfig bCryptConfig;

	// TODO: (추가) 휴대폰 인증, 약관 동의. 다른 사람의 email 혹은 phoneNumber를 선점하면 어떻게 하지??
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
		return userRepository.getUserById(id)
			.map(UserInfoResponse::from)
			.orElseThrow(() -> new ApiException(UserErrorCode.NOT_EXISTS_ID));
	}

	public UserInfoResponse updateUsername(
		CustomUserDetails userDetails,
		UpdateUsernameRequest updateUsernameRequest
	) {
		User user = userDetails.user();
		user.setUsername(updateUsernameRequest.username());
		return UserInfoResponse.from(userRepository.save(user));
	}

	public UserInfoResponse updatePhoneNumber(
		CustomUserDetails userDetails,
		UpdatePhoneNumberRequest updatePhoneNumberRequest
	) {
		User user = userDetails.user();
		user.setPhoneNumber(updatePhoneNumberRequest.phoneNumber());
		return UserInfoResponse.from(userRepository.save(user));
	}

	public UserInfoResponse updateUserPassword(
		CustomUserDetails userDetails,
		UpdateUserPasswordRequest updatePasswordRequest
	) {
		if (!bCryptConfig.passwordEncoder()
			.matches(updatePasswordRequest.oldPassword(), userDetails.getPassword())) {
			throw new ApiException(AuthErrorCode.INVALID_PASSWORD);
		}
		User user = userDetails.user();
		String updatedPassword = bCryptConfig.passwordEncoder().encode(updatePasswordRequest.newPassword());
		user.setPassword(updatedPassword);
		return UserInfoResponse.from(userRepository.save(user));
	}

	// TODO: (추가) 추후 유저에 관련된 데이터도 inactive. 유예 기간을 줘야겠는데
	public void deleteUser(CustomUserDetails userDetails) {
		User user = userDetails.user();
		user.setEnabled(false);
		userRepository.save(user);
	}
}
