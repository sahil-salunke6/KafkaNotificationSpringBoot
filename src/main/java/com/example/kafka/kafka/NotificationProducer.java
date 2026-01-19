//package com.example.kafka;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/notifications")
//public class NotificationProducer {
//
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    private static final String TOPIC = "notifications";
//
//    @PostMapping("/send")
//    public String sendNotification(@RequestParam String message) {
//        kafkaTemplate.send(TOPIC, message);
//        return "Message sent: " + message;
//    }
//}