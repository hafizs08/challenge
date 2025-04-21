package com.example.demo.service;

import com.example.demo.entity.Pengguna;

import java.util.List;

public interface PenggunaService {

    List<Pengguna> getAllPengguna();

    Pengguna getPenggunaById(Long id);

    Pengguna savePengguna(Pengguna pengguna);

    Pengguna updatePengguna(Long id, Pengguna pengguna);

    void deletePengguna(Long id);



}
