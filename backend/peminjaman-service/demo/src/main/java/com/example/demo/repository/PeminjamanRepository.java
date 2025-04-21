package com.example.demo.repository;

import com.example.demo.entity.Peminjaman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PeminjamanRepository extends JpaRepository<Peminjaman, Long> {
    List<Peminjaman> findByTanggalKembaliBetween(Date start, Date end);
    List<Peminjaman> findByPengguna(String pengguna);
   
}
