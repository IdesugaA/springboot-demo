package com.example.nacosdemo.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class testController {

    @Value("${test.id}")
    private String id;

    @GetMapping("/id")
    public String getId() {
        return id;
    }

}
