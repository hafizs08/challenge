package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.request.BukuRequest;
import com.example.demo.entity.Buku;

import java.util.List;
import java.util.Optional;

public interface BukuService {
    Buku tambahBuku(BukuRequest request, MultipartFile coverImage);
    List<Buku> getAllBuku();
    Optional<Buku> getBukuById(Long id);
    Buku updateBuku(Long id, BukuRequest request, MultipartFile coverImage);
    void deleteBuku(Long id);
}