package io.dev.simpleblog.service;

import io.dev.simpleblog.domain.user.User;
import io.dev.simpleblog.domain.user.UserDto;
import io.dev.simpleblog.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDto findUserByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(
                () -> new UsernameNotFoundException(
                    String.format("user not found [email: %s]", email)))
            .toDto();
    }


    @Transactional(readOnly = true)
    public UserDetailsService userDetailsService() {
        return email -> null;
    }

}
