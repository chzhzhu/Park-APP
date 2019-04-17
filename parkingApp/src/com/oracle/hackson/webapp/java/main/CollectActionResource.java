package com.oracle.hackson.webapp.java.main;

import com.google.gson.Gson;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("collection")
public class CollectActionResource {
    private String DB_NAME_TEST = "test";
    private String COLLECTION_NAME_USER = "userInfo";

    @POST
    @Path("unlock")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String unlockCollection(String payload) {
        String[] payloadStrs = payload.split(",");
        OrderResource orderResource = new OrderResource();
        EuqEventResource euqEventResource = new EuqEventResource();
        String orderInfo = "";
        String equInfo = "{" + payloadStrs[payloadStrs.length-3] + "," + payloadStrs[payloadStrs.length-2] + "," + payloadStrs[payloadStrs.length-1];
        for (int i = 0; i < payloadStrs.length-2 ; i++) {
            orderInfo += payloadStrs[i] + ",";
        }
        orderInfo += payloadStrs[payloadStrs.length-2] + "}";
        String orderResponse = orderResource.addOrder(orderInfo);
        if (orderResponse.equals("Success")) {
            String equResponse = euqEventResource.unlockEqu(equInfo);
            if (equResponse.equals("Unlock successfully")) {
                return "Success";
            } else {
                return "Failed with unlock";
            }
        } else {
            return "Failed with add order";
        }
    }

    @POST
    @Path("lock")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String lockCollection(String payload) {
        String[] payloadStrs = payload.split(",");
        String orderInfo = "";
        for (int i = 0; i < payloadStrs.length -2 ; i++) {
            orderInfo += payloadStrs[i] + ",";
        }
        orderInfo += payloadStrs[payloadStrs.length-2] + "}";
        String equInfo = "{" + payloadStrs[1] + "," + payloadStrs[payloadStrs.length-3] + "," + payloadStrs[payloadStrs.length-1];
        OrderResource orderResource = new OrderResource();
        EuqEventResource euqEventResource = new EuqEventResource();
        String orderResponse = orderResource.endOrder(orderInfo);
        if (orderResponse.equals("Success")) {
            String equResponse = euqEventResource.lockEqu(equInfo);
            if (equResponse.equals("Lock successfully")) {
                return "Success";
            } else {
                return "Failed with lock";
            }
        } else {
            return "Failed with end order";
        }
    }

    @POST
    @Path("pay")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String payCollection(String payload) {
        Gson json = new Gson();
        String[] payloadStrs = payload.split(",");
        OrderResource orderResource = new OrderResource();
        AccountResource accountResource = new AccountResource();
        String orderInfo = "";
        String accountInfo = payloadStrs[0] + "," + payloadStrs[payloadStrs.length-1];
        for (int i = 0; i < payloadStrs.length -2 ; i++) {
            orderInfo += payloadStrs[i] + ",";
        }
        orderInfo += payloadStrs[payloadStrs.length-2] + "}";
        User user = json.fromJson(accountInfo,User.class);
        int payPrice = user.getAccount();
        String username = user.getUsername();
        String queryUserStr = MongoDBUtils.findByUserName(DB_NAME_TEST, COLLECTION_NAME_USER, username);
        String[] queryUserStrs = queryUserStr.split(",");
        String str = "{";
        for (int i = 1; i < queryUserStrs.length-1; i++) {
            str += queryUserStrs[i] + ",";
        }
        str += queryUserStrs[queryUserStrs.length-1];
        User queryUser = json.fromJson(str,User.class);
        int accountMoney = queryUser.getAccount();
        if (accountMoney - payPrice >= 0) {
            String orderResponse = orderResource.setPayFlag(orderInfo);
            if (orderResponse.equals("Success")) {
                String accountResponse = accountResource.payPark(accountInfo);
                if (accountResponse.equals("Pay successfully")) {
                    return "Success";
                } else {
                    return "Failed with pay";
                }
            } else {
                return "Failed with set order flag";
            }
        } else {
            return "No enough money";
        }

    }

}
