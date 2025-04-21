package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Peminjaman;
import com.example.demo.repository.PeminjamanRepository;

@Service
public class PeminjamanScheduler {

    @Autowired
    private PeminjamanRepository peminjamanRepository;

    @Autowired
    private RestTemplate restTemplate; // Untuk kirim request ke service notif

    @Scheduled(cron = "0 0 8 * * ?") // Jalan setiap hari jam 8 pagi
    public void cekDanKirimPeringatan() {
        List<Peminjaman> peminjamanList = peminjamanRepository.findAll();

        Date today = new Date();
        for (Peminjaman p : peminjamanList) {
            if (p.getTanggalKembali() != null && p.getTanggalKembali().before(today)) {
                String emailPengguna = getEmailPengguna(p.getPengguna());
                if (emailPengguna != null) {
                    kirimNotifikasi(emailPengguna, p);
                }
            }
        }
    }

    private void kirimNotifikasi(String email, Peminjaman peminjaman) {
        String notifUrl = "http://localhost:8321/api/email/send";

        String subject = "Peringatan: Pengembalian Buku Terlambat!";
        String text = "Halo,\n\nAnda memiliki buku yang belum dikembalikan.\n"
                + "Judul Buku: " + peminjaman.getBuku() + "\n"
                + "Tanggal Kembali: " + peminjaman.getTanggalKembali() + "\n"
                + "Harap segera dikembalikan.\n\nTerima kasih.";

        restTemplate.getForObject(notifUrl + "?to=" + email + "&subject=" + subject + "&text=" + text, String.class);
    }

    private String getEmailPengguna(String idPengguna) {
        String userServiceUrl = "http://localhost:8032/pengguna/" + idPengguna;
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(userServiceUrl, Map.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return (String) response.getBody().get("email");
            }
        } catch (Exception e) {
            System.err.println("Gagal mengambil email pengguna: " + e.getMessage());
        }
        return null;
    }
}
