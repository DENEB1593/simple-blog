package io.dev.simpleblog.service;

import io.dev.simpleblog.domain.user.UserDto;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceTest {

    private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;

    @Test
    void joinTest() {
        var given = new UserDto("deneb@email.test", "deneb", "1234");

        var expected = userService.join(given);

        assertThat(expected).isNotNull();
        assertThat(expected).usingRecursiveComparison().comparingOnlyFields("email", "nickname");
    }

}