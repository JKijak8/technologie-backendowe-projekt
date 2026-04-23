package com.jkpbmz.technologiebackendoweprojekt.controllers;

import com.jkpbmz.technologiebackendoweprojekt.config.JwtConfig;
import com.jkpbmz.technologiebackendoweprojekt.entities.User;
import com.jkpbmz.technologiebackendoweprojekt.projections.auth.JwtResponse;
import com.jkpbmz.technologiebackendoweprojekt.projections.auth.LoginRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.user.UserRegisterRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.user.UserSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.repositories.RefreshTokenRepository;
import com.jkpbmz.technologiebackendoweprojekt.repositories.UserRepository;
import com.jkpbmz.technologiebackendoweprojekt.services.Jwt;
import com.jkpbmz.technologiebackendoweprojekt.services.JwtService;
import com.jkpbmz.technologiebackendoweprojekt.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
    private final UserService userService;

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

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(@CookieValue(value = "refreshToken") String refreshToken,
                                               HttpServletResponse response) {
        var jwt = jwtService.parseToken(refreshToken);
        if (jwt == null || jwt.isExpired()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var user = userRepository.findById(jwt.getId()).orElseThrow();
        var token = user.getRefreshTokens()
                .stream()
                .filter(t -> t.getToken().equals(refreshToken))
                .findFirst()
                .orElse(null);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        refreshTokenRepository.delete(token);

        Map<String, Jwt> tokens = generateTokens(user);

        var cookie = generateRefreshCookie(tokens.get(REFRESH_TOKEN).toString());
        response.addCookie(cookie);

        return ResponseEntity.ok(new JwtResponse(tokens.get(ACCESS_TOKEN).toString(),
                tokens.get(ACCESS_TOKEN).getExpiration()));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@CookieValue(value = "refreshToken") String refreshToken) {
        var jwt = jwtService.parseToken(refreshToken);
        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        refreshTokenRepository.findById(jwt.toString()).ifPresent(refreshTokenRepository::delete);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserSummaryDTO> register(@RequestBody UserRegisterRequest request,
                                                   UriComponentsBuilder uriBuilder) {
        UserSummaryDTO user = userService.createUser(request);
        URI uri = uriBuilder.path("/user?userId={id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping("/me")
    public UserSummaryDTO me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        Long id = (Long) authentication.getPrincipal();
        return userService.fetchUser(id);
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
