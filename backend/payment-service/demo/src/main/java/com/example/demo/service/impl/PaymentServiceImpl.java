package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.entity.Payment;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.service.PaymentService;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public Payment updatePayment(Long id, Payment paymentDetails) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment tidak ditemukan"));
        
        existingPayment.setIdBuku(paymentDetails.getIdBuku());
        existingPayment.setAmount(paymentDetails.getAmount());
        existingPayment.setPaymentMethod(paymentDetails.getPaymentMethod());
        existingPayment.setStatus(paymentDetails.getStatus());
        
        return paymentRepository.save(existingPayment);
    }

    @Override
    public void deletePayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment tidak ditemukan"));
        paymentRepository.deleteById(id);
    }
}
