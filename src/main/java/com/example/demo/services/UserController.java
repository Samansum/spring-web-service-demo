package com.example.demo.services;

import com.example.demo.models.NewUser;
import com.example.demo.persistence.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public String createUser(@RequestBody NewUser user) {
    return userService.insertUser(user);
  }

  @GetMapping("/{id}")
  public Document getUser(@PathVariable("id") String userId) {
    return userService.getUser(userId);
  }

  @GetMapping
  public List<Document> getAllUsers() {
    return userService.getUsers();
  }
}
