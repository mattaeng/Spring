package com.mattaeng.mattaengapi.service;

import org.springframework.stereotype.Service;

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

	public LoginResponse login(LoginRequest request) {
		return userRepository.getByUserId(request.userId())
			.map(user -> {
				if (!bCryptConfig.passwordEncoder().matches(request.password(), user.getPassword())) {
					throw new ApiException(AuthErrorCode.INVALID_PASSWORD);
				}
				String accessToken = jwtProvider.createAccessToken(request.userId());
				return LoginResponse.builder()
					.accessToken(accessToken)
					.build();
			})
			.orElseThrow(() -> new ApiException(AuthErrorCode.NOT_EXIST_EMAIL));
	}
}
