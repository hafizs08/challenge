package com.example.demo.repository;

import com.example.demo.entity.RatingBuku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingBukuRepository extends JpaRepository<RatingBuku, Long> {
    List<RatingBuku> findByBuku(String buku);
}

