package io.dev.simpleblog.config.security;

import io.dev.simpleblog.service.AuthService;
import io.fusionauth.jwt.domain.JWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtManager jwtManager;
    private final AuthService authService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // SecurityContext 내 인증정보 확인
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            // request으로부터 accessToken을 가져온다
            var authorization = request.getHeader("Authorization");

            // Header 내 인증정보를 검증한다.
            if (ObjectUtils.isEmpty(authorization) || !authorization.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            var accessToken = authorization.substring(7);
            log.debug("jwt token : {}", accessToken);

            var jwt = jwtManager.decode(accessToken);
            var userDetails = authService.loadUserByUsername(jwt.subject);

            // TODO jwt의 claim을 검증한다.


        }
        else {
            log.info("log in info : {}", SecurityContextHolder.getContext().getAuthentication());
        }

        filterChain.doFilter(request, response);

    }

}
