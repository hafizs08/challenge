package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rating_buku")
public class RatingBuku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ratingBuku;
    
    @Column(name = "id_buku")
    private String buku;

    @Column(name = "id_pengguna")
    private String pengguna;

    @Column(name = "rating")
    private int rating;

    @Column(name = "komentar")  
    private String komentar;

}


