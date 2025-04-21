package com.example.demo.service;

import com.example.demo.entity.Payment;
import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Payment createPayment(Payment payment);
    List<Payment> getAllPayments();
    Optional<Payment> getPaymentById(Long id);
    Payment updatePayment(Long id, Payment paymentDetails);
    void deletePayment(Long id);
}
