package com.example.demo.controller;


import com.example.demo.entity.Peminjaman;
import com.example.demo.repository.PeminjamanRepository;
import com.example.demo.service.PeminjamanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/peminjaman")
public class PeminjamanController {

    private final PeminjamanRepository peminjamanRepository;

    public PeminjamanController(PeminjamanRepository peminjamanRepository) {
        this.peminjamanRepository = peminjamanRepository;
    }

    @Autowired
    private PeminjamanService peminjamanService;

    @GetMapping("/mendekati-kembali")
    public List<Peminjaman> getPeminjamanMendekatiKembali() {
        Date today = new Date();
        Date besok = new Date(today.getTime() + (1000 * 60 * 60 * 24)); // +1 hari

        return peminjamanRepository.findByTanggalKembaliBetween(today, besok);
    }

    @GetMapping
    public List<Peminjaman> getAllPeminjaman() {
        return peminjamanService.getAllPeminjaman();
    }

    @GetMapping("/{id}")
    public Peminjaman getPeminjamanById(@PathVariable Long id) {
        return peminjamanService.getPeminjamanById(id);
    }

    @PostMapping
    public Peminjaman savePeminjaman(@RequestBody Peminjaman peminjaman) {
        return peminjamanService.savePeminjaman(peminjaman);
    }

    @PutMapping("/{id}")
    public Peminjaman updatePeminjaman(@PathVariable Long id, @RequestBody Peminjaman peminjaman) {
        return peminjamanService.updatePeminjaman(id, peminjaman);
    }

    @DeleteMapping("/{id}")
    public void deletePeminjaman(@PathVariable Long id) {
        peminjamanService.deletePeminjaman(id);
    }
    @GetMapping("/pengguna/{pengguna}")
    public List<Peminjaman> getPeminjamanByPengguna(@PathVariable String pengguna) {
        return peminjamanService.getPeminjamanByPengguna(pengguna);
    }
}