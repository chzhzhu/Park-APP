package com.oracle.hackson.webapp.java.main;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import com.oracle.hackson.webapp.simulation.Equipment;
import com.oracle.hackson.webapp.simulation.Simulation;
import org.bson.Document;

@Path("parkport")
public class EuqEventResource {
    private String DB_NAME = "parkingApp";
    private String COLLECTION_NAME_EQU = "equipment";
    private String COLLECTION_NAME_PARKPROT = "parkport";

    @POST
    @Path("unlock")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String unlockEqu(String equInfo){
        String equId = getEquId(equInfo);
        Document document = getDocument(equInfo);
        if(validateEqu(equId, "Unlocked")){
            MongoDBUtils.update(DB_NAME, COLLECTION_NAME_EQU,"equId",equId,document);
            return "Unlock successfully";
        }else{
            return "Unlock Failed";
        }
    }

    @POST
    @Path("lock")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String lockEqu(String equInfo){
        Document document = getDocument(equInfo);
        String equId = getEquId(equInfo);
        if(validateEqu(equId, "Locked")){
            MongoDBUtils.update(DB_NAME, COLLECTION_NAME_EQU,"equId",equId,document);
            return "Lock successfully";
        }else{
            return "Lock Failed";
        }
    }

    @GET
    @Path("parkport")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllEqus(@DefaultValue("1") @QueryParam("parkport") int parkPortId){
        String response = MongoDBUtils.findByPortId(DB_NAME, COLLECTION_NAME_PARKPROT,parkPortId);
        return response;
    }

    @GET
    @Path("equipment")
    @Produces(MediaType.TEXT_PLAIN)
    public String getEquByEquId(@DefaultValue("10001") @QueryParam("equId") int equId){
        String response = MongoDBUtils.findEquByEquId(DB_NAME, COLLECTION_NAME_EQU,equId);
        return response;
    }

    private String getEquId(String equInfo) {
        int startIndex = equInfo.indexOf(":");
        int endIndex = equInfo.indexOf(",");
        String equId = equInfo.substring(startIndex+1,endIndex);
        return equId;
    }

    private Document getDocument(String equInfo){
        String[] strings = equInfo.split(",");
        String jsonStr = "{"+strings[1];
        BasicDBObject dbObject = (BasicDBObject)JSON.parse(jsonStr);
        BasicDBObject update = new BasicDBObject("$set",dbObject);
        Document document = new Document(update.toMap());
        return document;
    }

    private boolean validateEqu(String equId, String event) {
        boolean status = false;
        String result = MongoDBUtils.findEquByEquId(DB_NAME, COLLECTION_NAME_EQU,Long.valueOf(equId));
        Gson gson = new Gson();
        Equipment equipment = gson.fromJson(result,Equipment.class);
        if(result == null) {
            return status;
        } else {
            if(equipment.isAlive() && !equipment.getEquStatus().matches(event)) {
                status = true;
            }
        }
        return status;
    }
}

