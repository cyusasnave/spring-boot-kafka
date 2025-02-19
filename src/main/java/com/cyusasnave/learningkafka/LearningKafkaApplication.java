package com.cyusasnave.learningkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class LearningKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningKafkaApplication.class, args);
	}

}
