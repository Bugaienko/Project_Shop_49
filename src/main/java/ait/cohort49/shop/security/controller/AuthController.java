package ait.cohort49.shop.security.controller;

import ait.cohort49.shop.security.dto.LoginRequestDTO;
import ait.cohort49.shop.security.dto.RefreshRequestDTO;
import ait.cohort49.shop.security.dto.TokenResponseDTO;
import ait.cohort49.shop.security.service.AuthService;
import jakarta.security.auth.message.AuthException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sergey Bugaenko
 * {@code @date} 17.12.2024
 */

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // POST "auth/login"
    @PostMapping("/login")
    public TokenResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {

        try {
            return authService.login(loginRequestDTO);
        } catch (AuthException e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping("/refresh")
    public  TokenResponseDTO refreshToken(@RequestBody RefreshRequestDTO refreshRequestDTO) {
        try {
            return authService.refreshAccessToken(refreshRequestDTO.getRefreshToken());
        } catch (AuthException e) {
            throw new RuntimeException(e);
        }
    }
}
