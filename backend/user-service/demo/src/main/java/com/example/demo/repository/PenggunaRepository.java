package com.example.demo.repository;

import com.example.demo.entity.Pengguna;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenggunaRepository extends JpaRepository<Pengguna, Long> {
    Pengguna findByEmail(String email);
    Optional<Pengguna> findByUsername(String username);
}