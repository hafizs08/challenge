package com.example.demo.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "peminjaman")

public class Peminjaman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_peminjaman")
    private Long idPeminjaman;

    @Column(name = "id_buku")
    private String buku;

    @Column(name = "id_pengguna")
    private String pengguna;

    @Temporal(TemporalType.DATE)
    private Date tanggalPinjam;

        @Temporal(TemporalType.DATE)
        private Date tanggalKembali;

    @Column(name = "status")
    private String status;

}
