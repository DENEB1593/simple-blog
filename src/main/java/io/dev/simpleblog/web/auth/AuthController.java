package io.dev.simpleblog.web.auth;

import io.dev.simpleblog.domain.auth.LoginDto;
import io.dev.simpleblog.domain.user.User;
import io.dev.simpleblog.domain.user.UserDto;
import io.dev.simpleblog.service.AuthService;
import io.dev.simpleblog.web.auth.LoginResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/join")
    public ResponseEntity<UserDto> join(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(authService.join(userDto));
    }

    @PostMapping
    public ResponseEntity<LoginResultDto> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }

}
