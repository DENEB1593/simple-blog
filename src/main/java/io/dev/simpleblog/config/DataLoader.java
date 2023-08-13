package io.dev.simpleblog.config;

import com.github.javafaker.Faker;
import io.dev.simpleblog.domain.user.User;
import io.dev.simpleblog.domain.user.UserRepository;
import io.dev.simpleblog.service.PostService;
import io.dev.simpleblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);
    private static final Faker faker = new Faker();

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationStartedEvent.class)
    void load() {
        log.info("=== data load start ===");

        /**
         * 사용자 저장
         */
        var users = new ArrayList<User>();
        IntStream.rangeClosed(1, 5).forEach(i -> {
            var user = new User(
                faker.internet().safeEmailAddress(),
                faker.funnyName().name(),
                passwordEncoder.encode("1234")
            );
            users.add(user);
        });
        userRepository.saveAll(users);


    }
}
