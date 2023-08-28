package io.dev.simpleblog.service;

import io.dev.simpleblog.config.security.JwtManager;
import io.dev.simpleblog.config.security.UserAuthentication;
import io.dev.simpleblog.web.dto.LoginDto;
import io.dev.simpleblog.domain.user.User;
import io.dev.simpleblog.domain.user.UserDto;
import io.dev.simpleblog.domain.user.UserRepository;
import io.dev.simpleblog.exception.NotFoundException;
import io.dev.simpleblog.web.dto.LoginResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtManager jwtManager;

    @Transactional
    public UserDto join(UserDto userDto) {
        return userRepository.save(userDto.toEntity(passwordEncoder)).toDto();
    }

    @Transactional(readOnly = true)
    public LoginResultDto login(LoginDto loginDto) {
        var user = userRepository.findByEmail(loginDto.email())
                .orElseThrow(() -> new NotFoundException(User.class, loginDto.email()));

        if (!user.getPassword().equals(passwordEncoder.encode(loginDto.password()))) {
            throw new RuntimeException("invalid password");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password()));

        var token = jwtManager.createToken(UserAuthentication.from(user));

        return new LoginResultDto(token);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(User.class, email));
        return UserAuthentication.from(user);
    }
}
