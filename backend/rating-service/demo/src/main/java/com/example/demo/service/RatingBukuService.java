package com.example.demo.service;

import com.example.demo.entity.RatingBuku;

import java.util.List;

public interface RatingBukuService {
    List<RatingBuku> getAllRatings();
    RatingBuku getRatingById(Long id);
    RatingBuku saveRating(RatingBuku ratingBuku);
    RatingBuku updateRating(Long id, RatingBuku ratingBuku);
    void deleteRating(Long id);
    List<RatingBuku> getRatingsByBuku(String buku);
}
