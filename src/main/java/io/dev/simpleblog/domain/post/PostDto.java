package io.dev.simpleblog.domain.post;

import io.dev.simpleblog.domain.user.User;

import java.time.LocalDateTime;

import static org.springframework.util.Assert.*;

public record PostDto(
        String title,
        String content,
        User user
) {

    public Post toEntity() {
        hasText(title, "title must provided");
        hasText(content, "content must provided");
        notNull(user, "empty user error");

        return new Post(title, content, user);
    }
}
