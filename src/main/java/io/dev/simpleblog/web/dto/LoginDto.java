package io.dev.simpleblog.web.dto;

public record LoginDto(
        String email,
        String password
) {
}
