package com.cyusasnave.learningkafka.config.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public <T> void sendMessage(String topic, T data) {
        try {
            kafkaTemplate.executeInTransaction(operations -> {
                operations.send(topic, data);
                return true;
            });
        } catch (Exception e) {
            log.error("Error sending message: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to send message to Kafka", e);
        }
    }
}
