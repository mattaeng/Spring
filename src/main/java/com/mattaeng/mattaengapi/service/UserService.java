package com.mattaeng.mattaengapi.service;

import org.springframework.stereotype.Service;

import com.mattaeng.mattaengapi.common.error.CommonErrorCode;
import com.mattaeng.mattaengapi.common.exception.ApiException;
import com.mattaeng.mattaengapi.domain.User;
import com.mattaeng.mattaengapi.dto.user.CreateUserRequest;
import com.mattaeng.mattaengapi.dto.user.CreateUserResponse;
import com.mattaeng.mattaengapi.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

	private final UserRepository userRepository;

	// TODO: (추가) 휴대폰 인증, 약관 동의
	public CreateUserResponse createUser(CreateUserRequest request) {
		if (userRepository.existsByUserIdOrPhoneNumber(request.userId(), request.phoneNumber())) {
			throw new ApiException(CommonErrorCode.BAD_REQUEST, "이미 존재하는 유저입니다."); // TODO: (수정) 유저 에러코드 정의
		}
		User user = userRepository.save(request.toEntity());
		return user.toCreateUserResponse();
	}
}
