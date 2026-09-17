package com.example.demo.services;

import com.example.demo.models.Greeting;
import com.example.demo.models.GreetingRecord;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  private final AtomicLong counter = new AtomicLong();

  @GetMapping("/hello")
  public HttpEntity<Greeting> hlo(
    @RequestParam(value = "name", defaultValue = "world") String name) {
    Greeting greeting = new Greeting(String.format("Hello %s!", name));

    Link link = WebMvcLinkBuilder.linkTo(
      WebMvcLinkBuilder.methodOn(GreetingController.class).hlo(name)).withSelfRel();

    greeting.add(link);

    return new ResponseEntity<>(greeting, HttpStatus.OK);
  }

  @GetMapping("/greeting")
  public HttpEntity<GreetingRecord> greeting(
    @RequestParam(value = "name", defaultValue = "buddy") String name) {
    GreetingRecord greeting = new GreetingRecord(
      counter.incrementAndGet(),
      String.format("Hello %s!", name)
    );

    return new ResponseEntity<>(greeting, HttpStatus.OK);
  }
}
