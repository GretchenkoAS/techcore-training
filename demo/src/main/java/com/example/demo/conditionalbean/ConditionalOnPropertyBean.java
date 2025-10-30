package com.example.demo.conditionalbean;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name="feature.toggle.enable", havingValue="true")
public class ConditionalOnPropertyBean {

    @PostConstruct
    public void init() {
        System.out.println("ConditionalOnPropertyBean created!");
    }
}
