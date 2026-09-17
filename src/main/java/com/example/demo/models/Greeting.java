package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

public class Greeting extends RepresentationModel<Greeting> {

  @Getter
  private final String content;

  @JsonCreator
  public Greeting(@JsonProperty("content") String content) {
    this.content = content;
  }
}
