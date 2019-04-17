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
        String userStr = json.toJson(user);
        if(loginValidation(user)){
            return "Duplicate User";
        }else{
            BasicDBObject dbObject = (BasicDBObject)JSON.parse(userStr);
            Document document = new Document(dbObject.toMap());
            MongoDBUtils.insert(DB_NAME,COLLECTION_NAME,document);
            return "Sign Up Successfully";
        }
    }

    @POST
    @Path("resetpasswd")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String resetPassWd(String userInfo){
        Gson json = new Gson();
        String[] userStrs = userInfo.split(",");
        String userOldInfo = userStrs[0].substring(1,userStrs[0].length()) + "," + userStrs[1];
        String userNewInfo = userStrs[2] + "," + userStrs[3].substring(0,userStrs[3].length()-1);
        User oldUser = json.fromJson(userOldInfo, User.class);
        User newUser = json.fromJson(userNewInfo,User.class);
        String username = oldUser.getUsername();
        String userRes = MongoDBUtils.findByUserName(DB_NAME,COLLECTION_NAME, username);
        if (!userRes.isEmpty()) {
            User queryUser = json.fromJson(userRes, User.class);
            String queryPasswd = queryUser.getPassword();
            String oldPasswd = oldUser.getPassword();
            if (queryPasswd.equals(oldPasswd)) {
                String userStr = json.toJson(newUser);
                Document document = MongoDBUtils.updateDocument(userStr);
                MongoDBUtils.updateStringField(DB_NAME, COLLECTION_NAME, "username", username, document);
                return "Reset Successfully";
            } else {
                return "Failed";
            }
        } else {
            return "Failed";
        }
    }

    public Boolean loginValidation(User inputUserInfo){
        Gson json = new Gson();
        String username = inputUserInfo.getUsername();
        String password = inputUserInfo.getPassword();
        String result = MongoDBUtils.findByUserName(DB_NAME,COLLECTION_NAME, username);
        System.out.println("resultjsonString:"+result);
        String userStr = "{";
        if(result!=null){
            String[] userStrs = result.split(",");
            for (int i = 1; i < userStrs.length - 1; i++) {
                userStr += userStrs[i] + ",";
            }
            userStr += userStrs[userStrs.length-1];
            User user = json.fromJson(userStr, User.class);
            if(user.getPassword().equals(password)){
                return true;
            }
        }
        return false;

    }

}
