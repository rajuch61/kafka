package com.nyl.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.nyl.kafka.model.Message;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "demo-topic")
    public void listen(Message message) {
        System.out.println("Consumed: " + message.getContent());
    }
}
