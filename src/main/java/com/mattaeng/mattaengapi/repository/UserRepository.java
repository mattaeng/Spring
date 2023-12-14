package com.mattaeng.mattaengapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mattaeng.mattaengapi.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Boolean existsByUserIdOrPhoneNumber(String userId, String phoneNumber);

	Optional<User> getByUserId(String userId);
}
