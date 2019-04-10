package com.oracle.hackson.webapp.java.main;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


import static com.mongodb.client.model.Filters.eq;


public class MongoDBUtils {

    private static final String HOST = "slc09ybj.us.oracle.com";
    private static final int PORT = 27017;

    public static MongoDatabase connectToDB(String databaseName){
        MongoClient CLIENT = new MongoClient(HOST, PORT);
        MongoDatabase mongoDB = CLIENT.getDatabase(databaseName);
        //System.out.println("DB:"+databaseName+" connect success!");
        return mongoDB;
    }

    public static MongoCollection getCollection(String databaseName,String collectionName ){
        MongoDatabase mongoDB = connectToDB(databaseName);
        MongoCollection collection = mongoDB.getCollection(collectionName);
        return collection;
    }


    public static void insert(String databaseName,String collectionName,Document dbObject){
        MongoCollection collection = getCollection(databaseName,collectionName);
        Document document = dbObject;
        collection.insertOne(document);
    }

    public static void update(String databaseName, String collectionName, String fieldName, String value, Document dbObject){
        MongoDatabase mongoDB = connectToDB(databaseName);
        MongoCollection collection = mongoDB.getCollection(collectionName);
        collection.updateMany(eq(fieldName,Long.valueOf(value)),dbObject);
    }

    public String findAllEquByParkPort(String databaseName,String collectionName,String parkPortId) {
        MongoCollection collection = getCollection(databaseName,collectionName);
        FindIterable<Document> findIterable = collection.find(eq("parkPortId", parkPortId));
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        StringBuilder allRestures = new StringBuilder();
        while(mongoCursor.hasNext()){
            Document document=((Document)mongoCursor.next());
            String result = document.toJson();
            allRestures.append(result +", ");
        }

        return allRestures.toString();
    }

    public static String findEquByEquId(String databaseName,String collectionName,long equipmentId) {
        String result = null;
        MongoCollection collection = getCollection(databaseName,collectionName);
        FindIterable<Document> findIterable = collection.find(eq("equId", equipmentId));
        Document document = (Document) findIterable.first();
        if (document != null){
            result = document.toJson();
        }
        return result;
    }

    public String findByUserName(String databaseName,String collectionName,String user){
        String result = null;
        MongoCollection collection = getCollection(databaseName,collectionName);
        FindIterable<Document> findIterable = collection.find(eq("username", user));
        Document document = (Document) findIterable.first();
        if (document != null){
            result = document.toJson();
        }
        return result;
    }

    public static String findByField (String databaseName,String collectionName, String fieldName, String key) {
        String result = null;
        MongoCollection collection = getCollection(databaseName,collectionName);
        FindIterable<Document> findIterable = collection.find(eq(fieldName, key));
        Document document = (Document) findIterable.first();
        if (document != null){
            result = document.toJson();
        }
        return result;
    }

    public static String findByPortId (String databaseName,String collectionName, int parkPortId) {
        String result = null;
        MongoCollection collection = getCollection(databaseName,collectionName);
        FindIterable<Document> findIterable = collection.find(eq("parkPortId", parkPortId));
        Document document = (Document) findIterable.first();
        if (document != null){
            result = document.toJson();
        }
        return result;
    }

}
