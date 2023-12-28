package com.mattaeng.mattaengapi.security.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mattaeng.mattaengapi.common.error.AuthErrorCode;
import com.mattaeng.mattaengapi.common.exception.ApiException;
import com.querydsl.core.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtProvider {

	private final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private final String AUTHENTICATION_TYPE = "Bearer";

	private final Long expiryMillisecondsAccessToken;
	private final JwtParser jwtParser;

	private final SecretKey secretKey;

	public JwtProvider(
		@Value("${jwt.secret}") String secret,
		@Value("${jwt.expiry-milliseconds-access-token}") Long expiryMillisecondsAccessToken
	) {
		this.expiryMillisecondsAccessToken = expiryMillisecondsAccessToken;
		this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secret));
		this.jwtParser = Jwts.parser().verifyWith(this.secretKey).build();
	}

	public String createAccessToken(String userId) {
		return createJws(userId, expiryMillisecondsAccessToken);
	}

	private String createJws(String userId, Long expiryDateMilliseconds) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + expiryDateMilliseconds);
		return Jwts.builder()
			.subject(userId)
			.expiration(expiryDate)
			.issuedAt(now)
			.signWith(secretKey)
			.compact();
	}

	public String extractJwsFromRequest(HttpServletRequest request) {
		String authorization = request.getHeader(this.AUTHORIZATION_HEADER_KEY);
		if (StringUtils.isNullOrEmpty(authorization)) {
			throw new ApiException(AuthErrorCode.EMPTY_AUTHORIZATION_HEADER);
		}
		if (!authorization.startsWith(AUTHENTICATION_TYPE)) {
			throw new ApiException(AuthErrorCode.INVALID_AUTHORIZATION_TYPE);
		}
		try {
			return authorization.substring(this.AUTHENTICATION_TYPE.length() + 1);
		} catch (IndexOutOfBoundsException e) {
			throw new ApiException(AuthErrorCode.EMPTY_TOKEN);
		}
	}

	public String getSubFromJws(String jws) {
		return getJwsClaims(jws)
			.getPayload()
			.getSubject();
	}

	private Jws<Claims> getJwsClaims(String jws) {
		try {
			return jwtParser.parseSignedClaims(jws);
		} catch (ExpiredJwtException e) {
			throw new ApiException(AuthErrorCode.EXPIRED_TOKEN);
		} catch (JwtException | IllegalArgumentException e) {
			throw new ApiException(AuthErrorCode.INVALID_TOKEN);
		}
	}

}
