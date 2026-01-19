package com.example.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaNotificationSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaNotificationSpringBootApplication.class, args);
        System.out.println("Kafka Notification Spring Boot Application Started");
    }
}