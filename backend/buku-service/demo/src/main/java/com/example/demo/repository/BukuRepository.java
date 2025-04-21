package com.example.demo.repository;

import com.example.demo.entity.Buku;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface BukuRepository extends JpaRepository<Buku, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Buku findById(long BukuId);
   
}
