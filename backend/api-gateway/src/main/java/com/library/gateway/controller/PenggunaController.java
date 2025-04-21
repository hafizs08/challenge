package com.library.gateway.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.library.gateway.entity.Pengguna;
import com.library.gateway.repository.PenggunaRepository;
import com.library.gateway.service.PenggunaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/pengguna")
public class PenggunaController {

    @Autowired
    private PenggunaService penggunaService;

    @Autowired
    private PenggunaRepository penggunaRepository;

    @GetMapping
    public List<Pengguna> getAllPengguna() {
        return penggunaService.getAllPengguna();
    }

    @GetMapping("/{id}")
    public Pengguna getPenggunaById(@PathVariable Long id) {
        return penggunaService.getPenggunaById(id);
    }

    @PostMapping
    public Pengguna savePengguna(@RequestBody Pengguna pengguna) {
        return penggunaService.savePengguna(pengguna);
    }

    @PutMapping("/{id}")
    public Pengguna updatePengguna(@PathVariable Long id, @RequestBody Pengguna pengguna) {
        return penggunaService.updatePengguna(id, pengguna);
    }

    @DeleteMapping("/{id}")
    public void deletePengguna(@PathVariable Long id) {
        penggunaService.deletePengguna(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Pengguna pengguna) {
    Pengguna penggunaDitemukan = penggunaRepository.findByEmail(pengguna.getEmail());

    if (penggunaDitemukan != null && penggunaDitemukan.getKataSandi().equals(pengguna.getKataSandi())) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("idPengguna", penggunaDitemukan.getIdPengguna());
        responseData.put("nama", penggunaDitemukan.getNama());
        responseData.put("username", penggunaDitemukan.getUsername());


        return ResponseEntity.ok(responseData);
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Gagal login. Periksa email dan kata sandi Anda.");
    }
}
}
