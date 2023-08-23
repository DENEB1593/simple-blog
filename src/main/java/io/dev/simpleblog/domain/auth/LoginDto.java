package io.dev.simpleblog.domain.auth;

public record LoginDto(
        String email,
        String password
) {
}
