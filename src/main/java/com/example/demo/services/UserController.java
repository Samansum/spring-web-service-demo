package com.example.demo.services;

import com.example.demo.models.NewUser;
import com.example.demo.persistence.MongoDbClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
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

  private final MongoDbClient mongoDbClient;

  @PostMapping
  public String createUser(@RequestBody NewUser user) {
    MongoCollection<NewUser> collection = mongoDbClient.getCollection("mydb", "users",
      NewUser.class);
    return collection.insertOne(user).getInsertedId().asObjectId().getValue().toString();
  }

  @GetMapping("/{id}")
  public NewUser getUser(@PathVariable("id") String userId) {
    MongoCollection<Document> collection = mongoDbClient.getCollection("mydb", "users");
    NewUser user = collection.find(Filters.eq("_id", new ObjectId(userId)), NewUser.class).first();
    return user;
  }

  @GetMapping
  public List<Document> getAllUsers() {
    FindIterable<Document> documents = mongoDbClient.getCollection("mydb", "users").find();
    List<Document> docs = new ArrayList<>();
    for (Document document : documents) {
      docs.add(document);
    }
    return docs;
  }
}
