package com.cyusasnave.learningkafka.order;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyusasnave.learningkafka.config.kafka.KafkaProducerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final KafkaProducerService kafkaProducer;

    @PostMapping
    public String createOrder(@RequestBody OrderDto request) {
        Order order = Order.builder()
                .userId(request.getUserId())
                .amount(request.getAmount())
                .build();
        kafkaProducer.sendMessage("order.created", order);
        return "Order placed: " + order.getOrderId();
    }
}
