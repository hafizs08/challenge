package com.example.demo.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "pengguna")
public class Pengguna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pengguna")
    private Long idPengguna;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "kata_sandi", nullable = false)
    private String kataSandi;

    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "email", unique = true, nullable = false)
    private String email;
}
