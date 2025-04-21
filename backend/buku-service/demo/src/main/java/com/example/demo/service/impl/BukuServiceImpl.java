package com.example.demo.service.impl;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.request.BukuRequest;
import com.example.demo.dto.request.EmailRequest;
import com.example.demo.entity.Buku;
import com.example.demo.repository.BukuRepository;
import com.example.demo.service.BukuPublisher;
import com.example.demo.service.BukuService;
import com.example.demo.service.MinioService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;

@Service
public class BukuServiceImpl implements BukuService {

    private final BukuRepository bukuRepository;
    private final MinioService minioService;
    private final RestTemplate restTemplate;
    private final String EMAIL_SERVICE_URL = "http://localhost:8321/api/email/send";
    private final BukuPublisher bukuPublisher;

    public BukuServiceImpl(BukuRepository bukuRepository, MinioService minioService,
            RestTemplate restTemplate, BukuPublisher bukuPublisher) {
        this.bukuRepository = bukuRepository;
        this.minioService = minioService;
        this.restTemplate = restTemplate;
        this.bukuPublisher = bukuPublisher;
    }

    // private String generateEmailBody(Buku buku) {
    // return "📖 Judul: " + buku.getJudul() + "\n" +
    // "✍️ Penulis: " + buku.getPenulis() + "\n" +
    // "📅 Tahun Terbit: " + buku.getTahunTerbit() + "\n" +
    // "📚 Jumlah Buku: " + buku.getJumlahBuku() + "\n" +
    // "⭐ Rata-Rata Rating: " + buku.getRataRataRating() + "\n\n" +
    // "Segera pinjam sebelum kehabisan!";
    // }

    // @Override
    // public Buku tambahBuku(BukuRequest request, MultipartFile coverImage) {
    // Buku buku = new Buku();
    // buku.setJudul(request.getJudul());
    // buku.setPenulis(request.getPenulis());
    // buku.setTahunTerbit(request.getTahunTerbit());
    // buku.setIsbn(request.getIsbn());
    // buku.setJumlahBuku(request.getJumlahBuku());

    // if (coverImage != null && !coverImage.isEmpty()) {
    // String imageUrl = minioService.uploadFile(coverImage);
    // buku.setGambar(imageUrl);
    // }

    // return bukuRepository.save(buku);
    // }

    @Override
    public Buku tambahBuku(BukuRequest request, MultipartFile coverImage) {
        Buku buku = new Buku();
        buku.setJudul(request.getJudul());
        buku.setPenulis(request.getPenulis());
        buku.setTahunTerbit(request.getTahunTerbit());
        buku.setIsbn(request.getIsbn());
        buku.setJumlahBuku(request.getJumlahBuku());

        if (coverImage != null && !coverImage.isEmpty()) {
            String imageUrl = minioService.uploadFile(coverImage);
            buku.setGambar(imageUrl);
        }

        // Simpan buku ke database
        Buku savedBuku = bukuRepository.save(buku);

        // Kirim notifikasi ke microservice notif
        kirimNotifikasiBukuBaru(savedBuku);

        return savedBuku;
    }

    private void kirimNotifikasiBukuBaru(Buku buku) {
        String notifUrl = "http://localhost:8321/api/email/send"; // URL microservice notif
        String subject = "Buku Baru Ditambahkan";
        String text = "Buku baru telah ditambahkan:\n"
                + "Judul: " + buku.getJudul() + "\n"
                + "Penulis: " + buku.getPenulis() + "\n"
                + "Tahun Terbit: " + buku.getTahunTerbit() + "\n"
                + "ISBN: " + buku.getIsbn() + "\n"
                + "Jumlah Buku: " + buku.getJumlahBuku();

        // Kirim HTTP request ke microservice notif
        restTemplate.getForObject(notifUrl + "?to=admin@example.com&subject=" + subject + "&text=" + text,
                String.class);
    }

    @Override
    public List<Buku> getAllBuku() {
        return bukuRepository.findAll();
    }

    @Override
    public Optional<Buku> getBukuById(Long id) {
        return bukuRepository.findById(id);
    }

    @Override
    public Buku updateBuku(Long id, BukuRequest request, MultipartFile file) {
        Buku existingBuku = bukuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Buku tidak ditemukan"));

        existingBuku.setJudul(request.getJudul());
        existingBuku.setPenulis(request.getPenulis());
        existingBuku.setTahunTerbit(request.getTahunTerbit());
        existingBuku.setJumlahBuku(request.getJumlahBuku());
        existingBuku.setIsbn(request.getIsbn());
        existingBuku.setTotalRating(request.getTotalRating());
        existingBuku.setJumlahPenggunaRating(request.getJumlahPenggunaRating());

        if (file != null && !file.isEmpty()) {
            String newFileUrl = minioService.uploadFile(file);
            existingBuku.setGambar(newFileUrl);
        }

        return bukuRepository.save(existingBuku);
    }

    @Override
    public void deleteBuku(Long id) {
        Buku buku = bukuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Buku tidak ditemukan"));

        if (buku.getGambar() != null) {
            String fileName = minioService.getObjectNameFromUrl(buku.getGambar());
            minioService.deleteFile(fileName);
        }

        bukuRepository.deleteById(id);
    }

    @Transactional
    @Lock(LockModeType.OPTIMISTIC)
    public void buyBuku(Long bukuId, int quantity) {
        Buku buku = bukuRepository.findById(bukuId)
                .orElseThrow(() -> new RuntimeException("Buku tidak ditemukan"));

        if (buku.getJumlahBuku() < quantity) {
            throw new RuntimeException("JumlahBuku tidak cukup");
        }

        buku.setJumlahBuku(buku.getJumlahBuku() - quantity);
        bukuRepository.save(buku);
    }
}