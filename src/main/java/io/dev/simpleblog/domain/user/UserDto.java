package io.dev.simpleblog.domain.user;


public record UserDto(
    String email,
    String nickname,
    String password
) {
}
