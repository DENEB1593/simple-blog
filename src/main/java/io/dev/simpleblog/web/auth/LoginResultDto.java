package io.dev.simpleblog.web.auth;

import java.time.LocalDateTime;

public record LoginResultDto(
        String accessToken
) {
}