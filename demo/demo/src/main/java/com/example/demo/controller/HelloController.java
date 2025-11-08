package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String getHello() {
        return "Hello  Andrew!";
    }

    @PostMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminMethod() {
        return "Only for admin!";
    }
}
