package com.mattaeng.mattaengapi.security.jwt;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final String SWAGGER_ENDPOINT = "/swagger-ui";
	private final String LOGIN_ENDPOINT = "/api/v1/login";
	private final String SIGNUP_ENDPOINT = "/api/v1/users";

	private final JwtProvider jwtProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
	) throws ServletException, IOException {
		if (!isExcludeUri(request.getRequestURI())) {
			String jws = jwtProvider.extractJwsFromRequest(request);
			jwtProvider.verifyJws(jws);
		}
		filterChain.doFilter(request, response);
	}

	private Boolean isExcludeUri(String uri) {
		return uri.startsWith(SWAGGER_ENDPOINT) ||
			uri.equals(LOGIN_ENDPOINT) ||
			uri.equals(SIGNUP_ENDPOINT);
	}
}
