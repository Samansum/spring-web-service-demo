package com.example.demo.services;

import com.example.demo.models.Greeting;
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

  @GetMapping("/hello")
  public HttpEntity<Greeting> hlo(
    @RequestParam(value = "name", defaultValue = "world") String name) {
    Greeting greeting = new Greeting(String.format("Hello %s!", name));

    Link link = WebMvcLinkBuilder.linkTo(
      WebMvcLinkBuilder.methodOn(GreetingController.class).hlo(name)).withSelfRel();

    greeting.add(link);

    return new ResponseEntity<>(greeting, HttpStatus.OK);
  }
}
