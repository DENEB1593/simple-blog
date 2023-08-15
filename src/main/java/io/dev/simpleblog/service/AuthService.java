package io.dev.simpleblog.service;

import io.dev.simpleblog.domain.user.UserDto;
import io.dev.simpleblog.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto join(UserDto userDto) {
        return userRepository.save(userDto.toEntity(passwordEncoder)).toDto();
    }
}
