package com.library.gateway.service;



import java.util.List;

import com.library.gateway.entity.Pengguna;

public interface PenggunaService {

    List<Pengguna> getAllPengguna();

    Pengguna getPenggunaById(Long id);

    Pengguna savePengguna(Pengguna pengguna);

    Pengguna updatePengguna(Long id, Pengguna pengguna);

    void deletePengguna(Long id);



}
