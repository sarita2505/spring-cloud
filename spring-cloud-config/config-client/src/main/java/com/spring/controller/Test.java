package com.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class Test {
    @Value("${msg}")
    String msg;
    @GetMapping("/hello")
    public String getMessage(){
        return msg;
    }
}
