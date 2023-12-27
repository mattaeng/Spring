package com.mattaeng.mattaengapi.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mattaeng.mattaengapi.domain.User;

public interface UserRepository extends JpaRepository<User, UUID> {

	Boolean existsByUserIdOrPhoneNumber(String userId, String phoneNumber);

	Optional<User> getByUserId(String userId);

	Optional<User> getUserById(UUID id);
}
