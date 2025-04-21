package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment")
    private Long idPayment;

    @Column(name = "id_buku")
    private String idBuku;

    @Column(name = "amount")
    private Integer amount;

    @Column(name ="payment_method")
    private String paymentMethod;

    @Column(name = "status")
    private String status;


}
