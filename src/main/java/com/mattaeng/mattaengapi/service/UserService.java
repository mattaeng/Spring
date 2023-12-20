package com.mattaeng.mattaengapi.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mattaeng.mattaengapi.common.error.CommonErrorCode;
import com.mattaeng.mattaengapi.common.error.UserErrorCode;
import com.mattaeng.mattaengapi.common.exception.ApiException;
import com.mattaeng.mattaengapi.domain.User;
import com.mattaeng.mattaengapi.dto.user.CreateUserRequest;
import com.mattaeng.mattaengapi.dto.user.CreateUserResponse;
import com.mattaeng.mattaengapi.dto.user.UserInfoResponse;
import com.mattaeng.mattaengapi.repository.UserRepository;
import com.mattaeng.mattaengapi.security.CustomUserDetails;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

	private final UserRepository userRepository;

	// TODO: (추가) 휴대폰 인증, 약관 동의
	public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
		if (userRepository.existsByUserIdOrPhoneNumber(createUserRequest.userId(), createUserRequest.phoneNumber())) {
			throw new ApiException(CommonErrorCode.BAD_REQUEST, "이미 존재하는 유저입니다."); // TODO: (수정) 유저 에러코드 정의
		}
		User user = userRepository.save(createUserRequest.toEntity());
		return user.toCreateUserResponse();
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
}
