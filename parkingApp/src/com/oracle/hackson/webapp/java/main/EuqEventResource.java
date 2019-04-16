package com.oracle.hackson.webapp.java.main;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.oracle.hackson.webapp.simulation.Equipment;
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
        String equId = getKeyValue(equInfo);
        Document document = getDocument(equInfo);
        if(validateEqu(equId, "Unlocked")){
            MongoDBUtils.updateIntField(DB_NAME, COLLECTION_NAME_EQU,"equId",equId,document);
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
        String equId = getKeyValue(equInfo);
        if(validateEqu(equId, "Locked")){
            MongoDBUtils.updateIntField(DB_NAME, COLLECTION_NAME_EQU,"equId",equId,document);
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

    public String getKeyValue(String info) {
        int startIndex = info.indexOf(":");
        int endIndex = info.indexOf(",");
        String keyValue = info.substring(startIndex+1,endIndex);
        return keyValue;
    }

    public Document getDocument(String info){
        String[] strings = info.split(",");
        String jsonStr = "{"+strings[1];
        Document document = MongoDBUtils.updateDocument(jsonStr);
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

