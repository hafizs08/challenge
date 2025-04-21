package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeminjamanDTO {
    private Long idPeminjaman;
    private String idBuku;
    private String idPengguna;
    private Date tanggalPinjam;
    private Date tanggalKembali;
    private String status;
}