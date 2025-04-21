package com.example.demo.controller;

import com.example.demo.config.JwtAuthenticationFilter;
import com.example.demo.dto.request.AuthRequest;
import com.example.demo.dto.response.AuthResponse;
import com.example.demo.entity.Pengguna;
import com.example.demo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
