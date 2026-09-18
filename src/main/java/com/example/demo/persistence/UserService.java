package com.example.demo.persistence;

import com.example.demo.models.NewUser;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final MongoDbClient mongoDbClient;

  public Document getUser(String userId) {
    return getCollection()
      .find(Filters.eq("_id", new ObjectId(userId))).first();
  }

  public List<Document> getUsers() {
    List<Document> documents = new ArrayList<>();
    for (Document document : getCollection().find()) {
      documents.add(document);
    }
    return documents;
  }

  public String insertUser(NewUser user) {
    return getCollection(NewUser.class)
      .insertOne(user)
      .getInsertedId()
      .asObjectId()
      .getValue()
      .toString();
  }

  private MongoCollection<Document> getCollection() {
    return mongoDbClient.getCollection("mydb", "users");
  }

  private <T> MongoCollection<T> getCollection(Class<T> clazz) {
    return mongoDbClient.getCollection("mydb", "users", clazz);
  }
}
