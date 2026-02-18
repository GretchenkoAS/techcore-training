package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @GetMapping("/fallback/books")
    public Mono<String> booksFallback() {
        return Mono.just("BookService is unavailable. Please try later.");
    }
}