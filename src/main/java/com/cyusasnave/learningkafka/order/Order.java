package com.cyusasnave.learningkafka.order;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder.Default;

@Getter
@Setter
@ToString
@Builder
public class Order {
    @Default
    private String orderId = UUID.randomUUID().toString();
    private String userId;
    private double amount;
}
