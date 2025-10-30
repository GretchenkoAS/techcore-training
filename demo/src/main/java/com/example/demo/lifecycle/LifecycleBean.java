package com.example.demo.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class LifecycleBean {

    @PostConstruct
    public void init() {
        System.out.println("Post construct!");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Pre destroy!");
    }
}