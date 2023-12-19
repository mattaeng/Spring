package com.mattaeng.mattaengapi.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mattaeng.mattaengapi.security.CustomUserDetails;
import com.mattaeng.mattaengapi.security.CustomUserDetailsService;

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
	private final CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
	) throws ServletException, IOException {
		if (!isExcludeUri(request.getRequestURI())) {
			String jws = jwtProvider.extractJwsFromRequest(request);
			String userId = jwtProvider.getSubFromJws(jws);
			CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(userId);
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				userDetails, userDetails.getPassword(), userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}

	private Boolean isExcludeUri(String uri) {
		return uri.startsWith(SWAGGER_ENDPOINT) ||
			uri.equals(LOGIN_ENDPOINT) ||
			uri.equals(SIGNUP_ENDPOINT);
	}
}
