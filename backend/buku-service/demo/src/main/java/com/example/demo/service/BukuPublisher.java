package com.example.demo.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class BukuPublisher {
    private final RabbitTemplate rabbitTemplate;

    public BukuPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void kirimNotifikasiBukuBaru(String pesan) {
        rabbitTemplate.convertAndSend("bukuExchange", "bukuRoutingKey", pesan);
        System.out.println("📤 Pesan dikirim ke RabbitMQ: " + pesan);
    }
}