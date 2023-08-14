package io.dev.simpleblog.domain.user;


import java.time.LocalDateTime;

public record UserDto(
    Long id,
    String email,
    String nickname,
    String password,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
