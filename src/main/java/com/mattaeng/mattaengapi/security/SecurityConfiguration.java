package com.mattaeng.mattaengapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mattaeng.mattaengapi.security.jwt.JwtAuthenticationFilter;
import com.mattaeng.mattaengapi.security.jwt.JwtExceptionFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final JwtExceptionFilter jwtExceptionFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.cors(Customizer.withDefaults())
			.csrf(AbstractHttpConfigurer::disable)
			.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.httpBasic(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable);

		http
			.authorizeHttpRequests(auth -> auth
				.requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
				.requestMatchers(HttpMethod.POST, "/api/v1/login").permitAll()
				.requestMatchers("/swagger-ui/**").permitAll()
				.requestMatchers("/**").permitAll() // TODO: 개발용
				.anyRequest().authenticated()
			);

		http
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter.class);

		return http.build();
	}
}
