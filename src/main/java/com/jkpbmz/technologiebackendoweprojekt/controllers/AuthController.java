package com.jkpbmz.technologiebackendoweprojekt.controllers;

import com.jkpbmz.technologiebackendoweprojekt.config.JwtConfig;
import com.jkpbmz.technologiebackendoweprojekt.entities.User;
import com.jkpbmz.technologiebackendoweprojekt.projections.auth.JwtResponse;
import com.jkpbmz.technologiebackendoweprojekt.projections.auth.LoginRequest;
import com.jkpbmz.technologiebackendoweprojekt.repositories.RefreshTokenRepository;
import com.jkpbmz.technologiebackendoweprojekt.repositories.UserRepository;
import com.jkpbmz.technologiebackendoweprojekt.services.Jwt;
import com.jkpbmz.technologiebackendoweprojekt.services.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final JwtConfig jwtConfig;
    private final RefreshTokenRepository refreshTokenRepository;

    private static final String ACCESS_TOKEN = "access";
    private static final String REFRESH_TOKEN = "refresh";

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request,
                                             HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        Map<String, Jwt> tokens = generateTokens(user);

        var cookie = generateRefreshCookie(tokens.get(REFRESH_TOKEN).toString());
        response.addCookie(cookie);

        Jwt accessToken = tokens.get(ACCESS_TOKEN);
        return ResponseEntity.ok(new JwtResponse(accessToken.toString(), accessToken.getExpiration()));
    }

    private Map<String, Jwt> generateTokens(User user) {
        Map<String, Jwt> tokens = new HashMap<>();

        tokens.put(ACCESS_TOKEN, jwtService.generateAccessToken(user));
        tokens.put(REFRESH_TOKEN, jwtService.generateRefreshToken(user));

        return tokens;
    }

    private Cookie generateRefreshCookie(String refreshToken) {
        var cookie = new Cookie("refreshToken", refreshToken);

        cookie.setHttpOnly(true);
        cookie.setPath("/auth/refresh");
        cookie.setMaxAge(jwtConfig.getRefreshTokenExpiration());
        cookie.setSecure(true);

        return cookie;
    }
}
