package com.example.demo.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  @GetMapping("/hello")
  public String hlo(@RequestParam(value = "name", defaultValue = "world") String name) {
    return String.format("Hello %s!", name);
  }
}
