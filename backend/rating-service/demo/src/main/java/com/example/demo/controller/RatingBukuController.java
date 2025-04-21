package com.example.demo.controller;

import com.example.demo.entity.RatingBuku;
import com.example.demo.service.RatingBukuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingBukuController {

    @Autowired
    private RatingBukuService ratingBukuService;

    @GetMapping
    public List<RatingBuku> getAllRatings() {
        return ratingBukuService.getAllRatings();
    }

    @GetMapping("/{id}")
    public RatingBuku getRatingById(@PathVariable Long id) {
        return ratingBukuService.getRatingById(id);
    }

    @PostMapping
    public RatingBuku saveRating(@RequestBody RatingBuku ratingBuku) {
        return ratingBukuService.saveRating(ratingBuku);
    }

    @PutMapping("/{id}")
    public RatingBuku updateRating(@PathVariable Long id, @RequestBody RatingBuku ratingBuku) {
        return ratingBukuService.updateRating(id, ratingBuku);
    }

    @DeleteMapping("/{id}")
    public void deleteRating(@PathVariable Long id) {
        ratingBukuService.deleteRating(id);
    }

    @GetMapping("/buku/{buku}")
    public List<RatingBuku> getRatingsByBuku(@PathVariable String buku) {
        return ratingBukuService.getRatingsByBuku(buku);
    }
}
