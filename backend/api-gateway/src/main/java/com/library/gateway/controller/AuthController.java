package com.library.gateway.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.library.gateway.config.JwtAuthenticationFilter;
import com.library.gateway.dto.request.AuthRequest;
import com.library.gateway.dto.response.AuthResponse;
import com.library.gateway.entity.Pengguna;
import com.library.gateway.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Pengguna> register(@RequestBody Pengguna pengguna) {
        return ResponseEntity.ok(authService.register(pengguna));
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid token");
        }
        String token = authHeader.substring(7);
        JwtAuthenticationFilter.blacklistToken(token);
        return ResponseEntity.ok("Logout berhasil");
    }
}
