package com.example.demo.dto.request;


import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BukuRequest {
    private Integer idBuku;
    @NotNull
    private String judul;
    @NotNull
    private String penulis;
    private int tahunTerbit;
    private String isbn;
    private int jumlahBuku;
}

