package com.cyusasnave.learningkafka.order;

import com.cyusasnave.learningkafka.config.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final KafkaProducerService kafkaProducer;

    @PostMapping
    public String createOrder(@RequestBody OrderDto request) {
        Order order = mapToDto(request);
        kafkaProducer.sendMessage("order.created", order);
        return "Order placed: " + order.getOrderId();
    }

    private static Order mapToDto(OrderDto request) {
        return Order.builder()
                    .userId(request.getUserId())
                    .amount(request.getAmount())
                    .build();
    }
}
