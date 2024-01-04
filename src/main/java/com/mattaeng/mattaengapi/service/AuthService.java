package com.mattaeng.mattaengapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mattaeng.mattaengapi.common.error.AuthErrorCode;
import com.mattaeng.mattaengapi.common.exception.ApiException;
import com.mattaeng.mattaengapi.dto.auth.LoginRequest;
import com.mattaeng.mattaengapi.dto.auth.LoginResponse;
import com.mattaeng.mattaengapi.repository.UserRepository;
import com.mattaeng.mattaengapi.security.BCryptConfig;
import com.mattaeng.mattaengapi.security.jwt.JwtProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final JwtProvider jwtProvider;
	private final BCryptConfig bCryptConfig;

	@Transactional(readOnly = true)
	public LoginResponse login(LoginRequest loginRequest) {
		return userRepository.getByUserId(loginRequest.userId())
			.map(user -> {
				if (!bCryptConfig.passwordEncoder().matches(loginRequest.password(), user.getPassword())) {
					throw new ApiException(AuthErrorCode.INVALID_PASSWORD);
				}
				String accessToken = jwtProvider.createAccessToken(loginRequest.userId());
				return LoginResponse.builder()
					.accessToken(accessToken)
					.build();
			})
			.orElseThrow(() -> new ApiException(AuthErrorCode.NOT_EXIST_USERID));
	}
}
