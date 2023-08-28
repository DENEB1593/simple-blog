package io.dev.simpleblog.config.security;

import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class JwtManager {

    private final JwtProperties properties;

    private Signer getSigner() {
        return HMACSigner.newSHA256Signer(properties.key());
    }

    public JWT decode(String accessToken) {
        var verifier = HMACVerifier.newVerifier(properties.key());
        return JWT.getDecoder().decode(accessToken, verifier);
    }

    public String createToken(UserAuthentication authentication) {
        var signer = getSigner();
        var jwt = new JWT()
                .setIssuer("simpleblog.dev.io")
                .setSubject(authentication.getUsername())
                .setIssuedAt(ZonedDateTime.now(ZoneOffset.systemDefault()))
                // cliam 추가 예정
                .setExpiration(ZonedDateTime.now().plusSeconds(properties.expiration()));

        return JWT.getEncoder().encode(jwt, signer);
    }

}
