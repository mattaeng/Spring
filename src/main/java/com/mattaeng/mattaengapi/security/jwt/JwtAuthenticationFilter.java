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

	private final JwtProvider jwtProvider;
	private final CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
	) throws ServletException, IOException {
		if (!isExcludeEndpoint(request.getMethod(), request.getRequestURI())) {
			String jws = jwtProvider.extractJwsFromRequest(request);
			String userId = jwtProvider.getSubFromJws(jws);
			CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(userId);
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				userDetails, userDetails.getPassword(), userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}

	private Boolean isExcludeEndpoint(String method, String uri) {
		final String GET_METHOD = "GET";
		final String POST_METHOD = "POST";

		final String SWAGGER_ENDPOINT = "/swagger-ui";
		final String API_DOCS_ENDPOINT = "/v3/api-docs";
		final String LOGIN_ENDPOINT = "/api/v1/login";
		final String SIGNUP_ENDPOINT = "/api/v1/users";

		return (uri.startsWith(SWAGGER_ENDPOINT) && method.equals(GET_METHOD)) ||
			(uri.startsWith(API_DOCS_ENDPOINT) && method.equals(GET_METHOD)) ||
			(uri.equals(LOGIN_ENDPOINT) && method.equals(POST_METHOD)) ||
			(uri.equals(SIGNUP_ENDPOINT) && method.equals(POST_METHOD));
	}
}
