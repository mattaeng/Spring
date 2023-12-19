package com.mattaeng.mattaengapi.security.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
	private final SecretKey key;

	public JwtProvider(
		@Value("${jwt.secret}") String secret,
		@Value("${jwt.expiry-milliseconds-access-token}") Long expiryMillisecondsAccessToken
	) {
		this.expiryMillisecondsAccessToken = expiryMillisecondsAccessToken;
		this.key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secret));
		this.jwtParser = Jwts.parser().verifyWith(this.key).build();
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
			.signWith(key)
			.compact();
	}

	public String extractJwsFromRequest(HttpServletRequest reqeust) {
		String authorization = reqeust.getHeader(this.AUTHORIZATION_HEADER_KEY);
		try {
			return authorization.substring(this.AUTHENTICATION_TYPE.length() + 1);
		} catch (Exception e) {
			return null;
		}
	}

	public void verifyJws(String jws) {
		try {
			jwtParser.parseSignedClaims(jws);
		} catch (JwtException e) {
			System.out.println(e.getMessage());
		}
	}
}
