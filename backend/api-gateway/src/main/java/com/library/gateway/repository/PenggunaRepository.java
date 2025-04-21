package com.library.gateway.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.gateway.entity.Pengguna;

@Repository
public interface PenggunaRepository extends JpaRepository<Pengguna, Long> {
    Pengguna findByEmail(String email);
    Optional<Pengguna> findByUsername(String username);
}
