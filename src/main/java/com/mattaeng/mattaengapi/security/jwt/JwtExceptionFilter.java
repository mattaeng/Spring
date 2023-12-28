package com.mattaeng.mattaengapi.security.jwt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mattaeng.mattaengapi.common.error.ErrorCodeIfs;
import com.mattaeng.mattaengapi.common.exception.ApiException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
	) throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (ApiException e) {
			setErrorResponse(response, e.getErrorCodeIfs());
		}
	}

	private void setErrorResponse(HttpServletResponse response, ErrorCodeIfs errorCode) {
		response.setStatus(errorCode.getHttpStatusCode());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			ErrorResponse.Error error = new ErrorResponse.Error(errorCode.getErrorCode(), errorCode.getDescription());
			ErrorResponse errorResponse = new ErrorResponse(error);
			response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
		} catch (IOException ignore) {
		}
	}

	private record ErrorResponse(Error error) {

		private record Error(
			String code,
			String description
		) {
		}
	}
}
