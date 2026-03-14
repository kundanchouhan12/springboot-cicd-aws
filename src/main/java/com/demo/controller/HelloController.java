package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from AWS CI/CD Pipeline 🚀";
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> healthInfo = new LinkedHashMap<>();
        healthInfo.put("status", "UP");
        healthInfo.put("application", "springboot-aws-cicd-demo");
        healthInfo.put("version", "1.0.0");
        healthInfo.put("timestamp", LocalDateTime.now().toString());
        return healthInfo;
    }

    @GetMapping("/time")
    public Map<String, String> time() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Map<String, String> timeInfo = new LinkedHashMap<>();
        timeInfo.put("currentTime", now.format(formatter));
        timeInfo.put("timezone", java.util.TimeZone.getDefault().getID());
        return timeInfo;
    }
}
