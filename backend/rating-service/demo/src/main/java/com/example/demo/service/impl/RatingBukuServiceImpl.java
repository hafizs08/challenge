package com.example.demo.service.impl;

import com.example.demo.entity.RatingBuku;
import com.example.demo.repository.RatingBukuRepository;
import com.example.demo.service.RatingBukuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingBukuServiceImpl implements RatingBukuService {

    @Autowired
    private RatingBukuRepository ratingBukuRepository;

    @Override
    public List<RatingBuku> getAllRatings() {
        return ratingBukuRepository.findAll();
    }

    @Override
    public RatingBuku getRatingById(Long id) {
        return ratingBukuRepository.findById(id).orElse(null);
    }

    @Override
    public RatingBuku saveRating(RatingBuku ratingBuku) {
        return ratingBukuRepository.save(ratingBuku);
    }

    @Override
    public RatingBuku updateRating(Long id, RatingBuku ratingBuku) {
        Optional<RatingBuku> existingRating = ratingBukuRepository.findById(id);

        if (existingRating.isPresent()) {
            RatingBuku updatedRating = existingRating.get();
            updatedRating.setBuku(ratingBuku.getBuku());
            updatedRating.setPengguna(ratingBuku.getPengguna());
            updatedRating.setRating(ratingBuku.getRating());
            updatedRating.setKomentar(ratingBuku.getKomentar());

            return ratingBukuRepository.save(updatedRating);
        } else {
            return null;
        }
    }

    @Override
    public void deleteRating(Long id) {
        ratingBukuRepository.deleteById(id);
    }

    @Override
    public List<RatingBuku> getRatingsByBuku(String buku) {
        return ratingBukuRepository.findByBuku(buku);
    }
}