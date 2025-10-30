package com.example.demo.runner;

import com.example.demo.service.MessageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final MessageService messageService;

    public StartupRunner(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(messageService.getMessageFromDb());
    }
}
