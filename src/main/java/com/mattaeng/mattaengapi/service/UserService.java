package com.mattaeng.mattaengapi.service;

import com.mattaeng.mattaengapi.common.error.ErrorCode;
import com.mattaeng.mattaengapi.common.exception.ApiException;
import com.mattaeng.mattaengapi.domain.User;
import com.mattaeng.mattaengapi.dto.user.CreateUserRequest;
import com.mattaeng.mattaengapi.dto.user.CreateUserResponse;
import com.mattaeng.mattaengapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        if (userRepository.existsByUsername(createUserRequest.getUsername())) {
            throw new ApiException(ErrorCode.BAD_REQUEST, "이미 존재하는 유저입니다.");
        }
        User user = userRepository.save(createUserRequest.toUser());
        return user.toCreateUserResponse();
    }
}
