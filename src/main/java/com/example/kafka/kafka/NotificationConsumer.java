//package com.example.kafka;
//
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class NotificationConsumer {
//
//    @KafkaListener(topics = "notifications", groupId = "spring-group")
//    public void consume(String message) {
//        System.out.println("Received message: " + message);
//    }
//}