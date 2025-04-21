package com.library.gateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.library.gateway.dto.response.AuthResponse;
import com.library.gateway.entity.Pengguna;
import com.library.gateway.lib.utility.JwtUtil;
import com.library.gateway.repository.PenggunaRepository;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final PenggunaRepository penggunaRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(PenggunaRepository penggunaRepository, JwtUtil jwtUtil) {
        this.penggunaRepository = penggunaRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // public String login(String username, String password) {
    // logger.info("Login attempt for username: {}", username);

    // Pengguna pengguna = penggunaRepository.findByUsername(username)
    // .orElseThrow(() -> {
    // logger.error("Username tidak ditemukan: {}", username);
    // return new RuntimeException("Username tidak ditemukan");
    // });

    // logger.info("Password dari database: {}", pengguna.getKataSandi());

    // if (!passwordEncoder.matches(password, pengguna.getKataSandi())) {
    // logger.error("Password salah untuk username: {}", username);
    // throw new RuntimeException("Password salah");
    // }

    // logger.info("Login berhasil untuk username: {}", username);
    // return jwtUtil.generateToken(username);
    // }
    public AuthResponse login(String username, String password) {
        logger.info("Login attempt for username: {}", username);

        Pengguna pengguna = penggunaRepository.findByUsername(username)
                .orElseThrow(() -> {
                    logger.error("Username tidak ditemukan: {}", username);
                    return new RuntimeException("Username tidak ditemukan");
                });

        logger.info("Password dari database: {}", pengguna.getKataSandi());

        if (!passwordEncoder.matches(password, pengguna.getKataSandi())) {
            logger.error("Password salah untuk username: {}", username);
            throw new RuntimeException("Password salah");
        }

        logger.info("Login berhasil untuk username: {}", username);

        // Generate token
        String token = jwtUtil.generateToken(username);

        // Return AuthResponse dengan token dan idPengguna
        return new AuthResponse(token, pengguna.getIdPengguna());
    }

    public Pengguna register(Pengguna pengguna) {
        pengguna.setKataSandi(passwordEncoder.encode(pengguna.getKataSandi()));
        return penggunaRepository.save(pengguna);
    }

}
