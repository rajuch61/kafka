package com.nyl.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "demo-topic")
    public void listen(String message) {
        System.out.println("Consumed: " + message);
    }
}
