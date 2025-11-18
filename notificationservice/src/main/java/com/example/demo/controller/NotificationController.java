package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @PostMapping("/notify")
    public String notify(@RequestBody String message) {
        System.out.println("Received notification: " + message);
        return "Notification received";
    }
}
