package com.example.demo.service;

import com.example.demo.entity.Peminjaman;

import java.util.List;

public interface PeminjamanService {

    List<Peminjaman> getAllPeminjaman();

    Peminjaman getPeminjamanById(Long id);

    Peminjaman savePeminjaman(Peminjaman peminjaman);

    Peminjaman updatePeminjaman(Long id, Peminjaman peminjaman);

    void deletePeminjaman(Long id);

    List<Peminjaman> getPeminjamanByPengguna(String pengguna);
}
