package com.example.helloworldapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("/")
    public String helloWorld() {
        return "hello world";
    }

    @GetMapping("/{name}")
    public String helloName(@PathVariable String name) {
        return "hello " + name;
    }
}
