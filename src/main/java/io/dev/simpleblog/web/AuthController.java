package io.dev.simpleblog.web;

import io.dev.simpleblog.domain.user.UserDto;
import io.dev.simpleblog.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/join")
    public ResponseEntity<UserDto> join(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(authService.join(userDto));
    }

}
