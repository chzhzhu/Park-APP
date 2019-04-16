package com.oracle.hackson.webapp.java.main;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import org.bson.Document;
import java.util.Date;

@Path("order")
public class OrderResource {
    private String DB_NAME = "test";
    private String COLLECTION_NAME = "orderInfo";

    @GET
    @Path("allOrderHistory")
    @Produces(MediaType.TEXT_PLAIN)
    public String getorderHisByUser(@DefaultValue("18661096693") @QueryParam("username") String username){
        String response = MongoDBUtils.findAllOrdsByUserName(DB_NAME, COLLECTION_NAME, username);
        return response;
    }

    @GET
    @Path("orderHistory")
    @Produces(MediaType.TEXT_PLAIN)
    public String getorderHisByOrderId(@DefaultValue("1") @QueryParam("orderId") int orderId){
        String response = MongoDBUtils.findOrderByOrderId(DB_NAME, COLLECTION_NAME, orderId);
        return response;
    }

    @POST
    @Path("addOrder")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addOrder (String orderInfo) {
        Gson json = new Gson();
        boolean createFlag = true;
        OrderInfo order = json.fromJson(orderInfo, OrderInfo.class);
        Date startDate = new Date();
        String username = order.getUsername();
        String result = MongoDBUtils.findAllOrdsByUserName(DB_NAME, COLLECTION_NAME, username);
        String[] resultStrs = result.split("/n");
        if (result != null && !result.isEmpty()) {
            for (int i = 0; i < resultStrs.length; i++) {
                OrderInfo queryOrder = json.fromJson(resultStrs[i], OrderInfo.class);
                createFlag = queryOrder.isPayFlag();
                if (!createFlag) {
                    break;
                }
            }
        }
        if (createFlag) {
            order.setStartTime(startDate);
            order.setPayFlag(false);
            order.setOrderId((int) MongoDBUtils.countCollection(DB_NAME, COLLECTION_NAME)+1);
            Document orderDocument = getinsDocument(order);
            MongoDBUtils.insert(DB_NAME, COLLECTION_NAME, orderDocument);
            return "Success";
        } else {
            return "Please pay your opened order";
        }
    }

    @POST
    @Path("endOrder")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String endOrder (String orderInfo) {
        Gson json = new Gson();
        OrderInfo order = json.fromJson(orderInfo, OrderInfo.class);
        Date endDate = new Date();
        int orderId = order.getOrderId();
        String result = getQueryOrder(order);
        if (result != null) {
            String[] resultStrs = result.split(",");
            String resultStr = "{";
            for (int i = 1; i < resultStrs.length - 1; i++) {
                resultStr += resultStrs[i] + ",";
            }
            resultStr += resultStrs[resultStrs.length - 1];
            OrderInfo endOrder = json.fromJson(resultStr, OrderInfo.class);
            if (endOrder.getEndTime() == null) {
                endOrder.setEndTime(endDate);
                endOrder.setPrice();
                String orderStr = json.toJson(endOrder);
                Document orderDocument = MongoDBUtils.updateDocument(orderStr);
                MongoDBUtils.updateIntField(DB_NAME, COLLECTION_NAME, "orderId", String.valueOf(orderId), orderDocument);
                return "Success";
            } else {
                return "This order was closed";
            }
        } else {
            return "Failed";
        }
    }

    @POST
    @Path("pay")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String setPayFlag (String orderInfo) {
        Gson json = new Gson();
        OrderInfo order = json.fromJson(orderInfo, OrderInfo.class);
        int orderId = order.getOrderId();
        String result = getQueryOrder(order);
        if (result != null) {
            OrderInfo queryOrder = json.fromJson(result, OrderInfo.class);
            if (queryOrder.getEndTime() != null && !queryOrder.isPayFlag()) {
                Document orderDocument = MongoDBUtils.updateDocument(orderInfo);
                MongoDBUtils.updateIntField(DB_NAME, COLLECTION_NAME, "orderId", String.valueOf(orderId), orderDocument);
                return "Success";
            } else {
                return "Not closed order or payed order";
            }
        } else {
            return "Failed";
        }
    }

    private Document getinsDocument (OrderInfo order) {
        Gson json = new Gson();
        String orderStr = json.toJson(order);
        BasicDBObject dbObjectEqu = (BasicDBObject) JSON.parse(orderStr);
        Document orderDocument= new Document(dbObjectEqu.toMap());
        return orderDocument;
    }

    private String getQueryOrder (OrderInfo order) {
        int orderId = order.getOrderId();
        String result = MongoDBUtils.findOrderByOrderId(DB_NAME, COLLECTION_NAME, orderId);
        return result;
    }

}
