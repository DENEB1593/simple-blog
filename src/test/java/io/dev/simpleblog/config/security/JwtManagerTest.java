package io.dev.simpleblog.config.security;

import io.dev.simpleblog.domain.user.User;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACVerifier;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class JwtManagerTest {

    static final Logger log = LoggerFactory.getLogger(JwtManagerTest.class);

    @Autowired
    JwtManager jwtManager;

    @Test
    void whenCreateToken_ThenReturnAccessToken() {
        var giveUserAuth = givenUserAuthentication();

        var accessToken = jwtManager.createToken(giveUserAuth);

        log.info("### generated access token : {}", accessToken);

        assertThat(accessToken).isNotNull();

    }

    @Test
    void givenAccessToken_whenEncodedToken_ThenEquals() {
        var givenUserAuth = givenUserAuthentication();

        var accessToken = jwtManager.createToken(givenUserAuth);

        assertThat(givenUserAuth.getUsername()).isEqualTo(jwtManager.decode(accessToken).subject);
    }

    private static UserAuthentication givenUserAuthentication() {
        var givenUser = new User("test@emai.test", "test_nickname", "1234");
        return UserAuthentication.from(givenUser);
    }


}