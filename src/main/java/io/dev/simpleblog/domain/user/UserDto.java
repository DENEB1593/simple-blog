package io.dev.simpleblog.domain.user;

import org.springframework.security.crypto.password.PasswordEncoder;

public record UserDto(
    String email,
    String nickname,
    String password
) {

    public User toEntity(PasswordEncoder encoder) {
        return new User(
            email,
            nickname,
            encoder.encode(password)
        );
    }
}
