package com.example.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "bukuQueue";

    @Bean
    public Queue bukuQueue() {
        return new Queue(QUEUE_NAME, true);
    }
}
