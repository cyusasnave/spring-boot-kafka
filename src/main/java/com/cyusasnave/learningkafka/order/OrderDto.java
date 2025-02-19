package com.cyusasnave.learningkafka.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderDto {
    private String userId;
    private double amount;
}
