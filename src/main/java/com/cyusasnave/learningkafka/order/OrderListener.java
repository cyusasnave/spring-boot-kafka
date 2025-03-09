package com.cyusasnave.learningkafka.order;

import java.text.MessageFormat;
import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    @KafkaListener(
            topics = "order.created",
            groupId = "learning-kafka",
            containerFactory = "concurrentKafkaListenerContainerFactory"
    )
    public void consumeOrder(@Payload Map<String, Object> payload, Acknowledgment acknowledgment) {
        Order sentOrder = buildOrderFromPayload(payload);
        System.out.println(MessageFormat.format("Received Order: {0}", sentOrder));
        acknowledgment.acknowledge();
    }

    private Order buildOrderFromPayload(final Map<String, Object> payload) {
        return Order.builder()
                    .orderId(payload.get("orderId").toString())
                    .userId(payload.get("userId").toString())
                    .amount(Double.parseDouble(payload.get("amount").toString()))
                    .build();
    }
}
