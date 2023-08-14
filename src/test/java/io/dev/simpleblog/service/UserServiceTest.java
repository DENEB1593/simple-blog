package io.dev.simpleblog.service;

import io.dev.simpleblog.domain.user.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class UserServiceTest {

    static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    UserService userService;

    UserDto givenUser;

    @BeforeEach
    void init() {
        givenUser = new UserDto(
                "deneb@email.test",
                "deneb",
                "1234"
        );
    }


    @Test
    void whenJoinRequest_ShouldSaveUser() {

        var expected = userService.join(givenUser);

        assertThat(expected).isNotNull();
        assertThat(expected).usingRecursiveComparison().comparingOnlyFields("email", "nickname");
    }

    @Test
    void whenFindUserByEmail_ShouldFounded() {
        userService.join(givenUser);

        UserDto expected = userService.findUserByEmail(givenUser.email());

        assertThat(expected).isNotNull();
        assertThat(expected.email()).isEqualTo(givenUser.email());
    }

}