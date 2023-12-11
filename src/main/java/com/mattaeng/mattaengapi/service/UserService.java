package com.mattaeng.mattaengapi.service;

import com.mattaeng.mattaengapi.domain.User;
import com.mattaeng.mattaengapi.dto.user.CreateUserRequest;
import com.mattaeng.mattaengapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUser(CreateUserRequest createUserRequest) {
        // TODO: 이미 존재하는 유저인 경우 예외처리
        if (userRepository.existsByUsername(createUserRequest.getUsername())) {
            return;
        }
        userRepository.save(createUserRequest.toUser());
    }
}
