package com.oracle.hackson.webapp.java.main;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import org.bson.Document;


@Path("hello")
public class LoginResource {

    private String DB_NAME = "test";
    private String COLLECTION_NAME = "userInfo";

    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello";
    }

    @POST
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String logIn(String userInfo){
        Gson json = new Gson();
        User user = json.fromJson(userInfo, User.class);
        if(loginValidation(user)){
            return "login success";
        }else{
            return "login fail";
        }
    }

    @POST
    @Path("signup")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String signUp(String userInfo){
        Gson json = new Gson();
        User user = json.fromJson(userInfo, User.class);
        if(loginValidation(user)){
            return "Duplicate User";
        }else{
            BasicDBObject dbObject = (BasicDBObject)JSON.parse(userInfo);
            Document document = new Document(dbObject.toMap());
            MongoDBUtils.insert(DB_NAME,COLLECTION_NAME,document);
            return "Sign Up Successfully";
        }
    }

    public Boolean loginValidation(User inputUserInfo){
        Gson json = new Gson();
        String username = inputUserInfo.getUsername();
        String password = inputUserInfo.getPassword();
        String result = MongoDBUtils.findByUserName(DB_NAME,COLLECTION_NAME, username);
        System.out.println("resultjsonString:"+result);
        if(result!=null){
            User user = json.fromJson(result, User.class);
            if(user.getPassword().equals(password)){
                return true;
            }
        }
        return false;

    }

}
