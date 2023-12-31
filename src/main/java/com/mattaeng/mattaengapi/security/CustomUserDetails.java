package com.mattaeng.mattaengapi.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mattaeng.mattaengapi.domain.User;

public record CustomUserDetails(User user) implements UserDetails {

	public UUID getId() {
		return user.getId();
	}

	public String getUserId() {
		return user.getUserId();
	}

	public String getPhoneNumber() {
		return user.getPhoneNumber();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.getIsAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return user.getIsEnabled();
	}
}
