package com.microservice.producerservice.producerservice.controller;

import com.microservice.producerservice.producerservice.dto.UserDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final RabbitTemplate rabbitTemplate;

    public UserController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody UserDto user) {
        try {

            Object response = rabbitTemplate.convertSendAndReceive("userQueue", user);

            if (response != null) {
                return ResponseEntity.ok(response.toString());
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("No response from user-service");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to communicate with user-service: " + e.getMessage());
        }
    }
}
