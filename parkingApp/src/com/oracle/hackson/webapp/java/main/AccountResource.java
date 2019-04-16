package com.oracle.hackson.webapp.java.main;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;
import org.bson.Document;

@Path("account")
public class AccountResource {
    private String DB_NAME = "test";
    private String COLLECTION_NAME = "userInfo";

    @GET
    @Path("money")
    @Produces(MediaType.TEXT_PLAIN)
    public String getUserAccount(@DefaultValue("18661096693") @QueryParam("username") String username){
        String response = MongoDBUtils.findByUserName(DB_NAME, COLLECTION_NAME, username);
        return response;
    }

    @POST
    @Path("addmoney")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String setUserAccount(String accountInfo) {
        Gson json = new Gson();
        User user = json.fromJson(accountInfo, User.class);
        int changeMoney = user.getAccount();
        String username = user.getUsername();
        String result = getQueryAccount(user);
        if (result != null) {
            User userQuery = json.fromJson(result, User.class);
            int accountMoney = userQuery.getAccount();
            user.setAccount(accountMoney + changeMoney);
            String userStr = json.toJson(user);
            String updateStr= getUpdateStr(userStr);
            Document document = MongoDBUtils.updateDocument(updateStr);
            if (accountMoney >= 0) {
                MongoDBUtils.updateStringField(DB_NAME, COLLECTION_NAME, "username", username, document);
                return "Set account successfully";
            } else {
                return "Set account failed";
            }
        } else {
            return "No such account";
        }
    }

    @POST
    @Path("pay")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String payPark(String accountInfo) {
        Gson json = new Gson();
        User user = json.fromJson(accountInfo, User.class);
        String result = getQueryAccount(user);
        if (result != null) {
            User userQuery = json.fromJson(result, User.class);
            int accountMoney = userQuery.getAccount();
            int changeMoney = user.getAccount();
            String username = user.getUsername();
            user.setAccount(accountMoney - changeMoney);
            String userStr = json.toJson(user);
            String updateStr = getUpdateStr(userStr);
            Document document = MongoDBUtils.updateDocument(updateStr);
            if (accountMoney >= 0) {
                MongoDBUtils.updateStringField(DB_NAME, COLLECTION_NAME, "username", username, document);
                return "Pay successfully";
            } else {
                return "Pay failed";
            }
        } else {
            return "Pay failed";
        }
    }


    private String getQueryAccount (User user) {
        String username = user.getUsername();
        String result = MongoDBUtils.findByUserName(DB_NAME, COLLECTION_NAME, username);
        return result;
    }

    private String getUpdateStr (String userStr) {
        String[] userStrs = userStr.split(",");
        String updateStr= userStrs[0] + "," + userStrs[2];
        return updateStr;
    }
}
