package com.mattaeng.mattaengapi.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.mattaeng.mattaengapi.common.error.AuthErrorCode;
import com.mattaeng.mattaengapi.common.exception.ApiException;
import com.mattaeng.mattaengapi.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.getByUserId(username)
			.map(CustomUserDetails::new)
			.orElseThrow(() -> new ApiException(AuthErrorCode.NOT_EXIST_USERID));
	}
}
