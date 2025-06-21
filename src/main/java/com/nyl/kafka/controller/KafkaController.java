package com.nyl.kafka.controller;

import org.springframework.web.bind.annotation.*;

import com.nyl.kafka.service.KafkaProducerService;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaProducerService producerService;

    public KafkaController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/publish")
    public String sendMessage(@RequestBody String message) {
        producerService.sendMessage(message);
        return "Message sent to Kafka";
    }
}

