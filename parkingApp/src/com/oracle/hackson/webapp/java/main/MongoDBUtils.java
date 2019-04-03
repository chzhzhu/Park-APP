package com.oracle.hackson.webapp.java.main;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.ws.rs.PathParam;

import static com.mongodb.client.model.Filters.eq;


public class MongoDBUtils {

    private static final String HOST = "slc09ybj.us.oracle.com";
    private static final int PORT = 27017;

    public void connectToDB(String databaseName){
        MongoClient CLIENT = new MongoClient(HOST, PORT);
        MongoDatabase DB = CLIENT.getDatabase(databaseName);
        System.out.println("DB:"+databaseName+" connect success!");
    }

    public void insert(String databaseName,String collectionName){
        MongoClient CLIENT = new MongoClient(HOST, PORT);
        MongoDatabase DB = CLIENT.getDatabase(databaseName);
        MongoCollection COLLECTION = DB.getCollection(collectionName);
        BasicDBObject document = new BasicDBObject();
        document.put("username", "1");
        document.put("password", "12345");
        COLLECTION.insertOne(document);
    }

    public String findByUserName(String databaseName,String collectionName,String user){
        MongoClient CLIENT = new MongoClient(HOST, PORT);
        MongoDatabase DB = CLIENT.getDatabase(databaseName);
        System.out.println("db connected");
        MongoCollection COLLECTION = DB.getCollection(collectionName);
        System.out.println("collection:"+COLLECTION);
        FindIterable<Document> findIterable = COLLECTION.find(eq("username", user));
        System.out.println("findIterable:"+findIterable);
        Document document = (Document) findIterable.first();
        System.out.println(document);
        String result = document.toJson();

        return result;
    }




}
