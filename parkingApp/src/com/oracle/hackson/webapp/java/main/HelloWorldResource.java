package com.oracle.hackson.webapp.java.main;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;
import com.mongodb.util.JSON;

import java.lang.annotation.Documented;


@Path("hello")
public class HelloWorldResource {

    public static final String CLICHED_MESSAGE = "Jersey Start:";
    public String kfkStr;
    public long kfkOffse;

    private String DB_NAME = "test";
    private String COLLECTION_NAME = "userInfo";

    @GET
    @Produces("text/plain")
    public String getHello() {
        return CLICHED_MESSAGE;
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

    public Boolean loginValidation(User inputUserInfo){
        MongoDBUtils dbUtils = new MongoDBUtils();
        Gson json = new Gson();
        String username = inputUserInfo.getUsername();
        String password = inputUserInfo.getPassword();
        System.out.println("username:"+username);
        System.out.println("password:"+password);
        String result = dbUtils.findByUserName(DB_NAME,COLLECTION_NAME, username);
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
