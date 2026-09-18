package com.example.demo.persistence;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MongoDbClient {

  private final Environment env;
  private MongoClient client = null;

  public MongoDatabase getDatabase(String databaseName) {
    return getClient().getDatabase(databaseName);
  }

  public MongoCollection<Document> getCollection(String databaseName, String collectionName) {
    return this.getDatabase(databaseName)
      .getCollection(collectionName);
  }

  public <T> MongoCollection<T> getCollection(String databaseName, String collectionName,
    Class<T> clazz) {
    return this.getDatabase(databaseName)
      .getCollection(collectionName, clazz);
  }

  private MongoClient getClient() {
    if (client == null) {
      String dbUser = env.getProperty("DB_USER");
      String dbPwd = env.getProperty("DB_PWD");
      String connectionString = String.format(
        "mongodb+srv://%s:%s@cluster0.stdxr0e.mongodb.net/?appName=Cluster0",
        dbUser,
        dbPwd
      );
      client = MongoClients.create(connectionString);
    }
    return client;
  }
}
