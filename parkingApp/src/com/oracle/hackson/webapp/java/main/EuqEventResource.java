package com.oracle.hackson.webapp.java.main;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.oracle.hackson.webapp.simulation.Equipment;
import com.oracle.hackson.webapp.simulation.ParkPort;
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
        String[] payloadStrs= equInfo.split(",");
        String parkProtId = getKeyValue(payloadStrs[0]);
        String equId = getKeyValue(payloadStrs[1]);
        String equPayload = "{" + payloadStrs[1] + "," +payloadStrs[2];
        Document document = getDocument(equPayload);
        if(validateEqu(equId, "Unlocked")){
            String response = MongoDBUtils.findByPortId(DB_NAME,COLLECTION_NAME_PARKPROT,Integer.valueOf(parkProtId));
            response.replace(" ", "");
            String[] responseStrs = response.split(",");
            String responseStr = "{";
            for (int i = 1; i < responseStrs.length-1; i++) {
                responseStr += responseStrs[i] + ",";
            }
            responseStr += responseStrs[responseStrs.length-1];
            Gson json = new Gson();
            ParkPort parkPort = json.fromJson(responseStr, ParkPort.class);
            parkPort.getEquByEquId(Integer.valueOf(equId)).setEquStatus("Unlocked");
            parkPort.setUnlockedEquNum();
            String parkPortStr = json.toJson(parkPort);
            Document parkPortDoc = MongoDBUtils.updateDocument(parkPortStr);
            MongoDBUtils.updateIntField(DB_NAME, COLLECTION_NAME_PARKPROT,"parkPortId",parkProtId,parkPortDoc);
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
        String[] payloadStrs= equInfo.split(",");
        String parkProtId = getKeyValue(payloadStrs[0]);
        String equId = getKeyValue(payloadStrs[1]);
        String equPayload = "{" + payloadStrs[1] + "," +payloadStrs[2];
        Document document = getDocument(equPayload);
        if(validateEqu(equId, "Locked")){
            String response = MongoDBUtils.findByPortId(DB_NAME,COLLECTION_NAME_PARKPROT,Integer.valueOf(parkProtId));
            response.replace(" ", "");
            String[] responseStrs = response.split(",");
            String responseStr = "{";
            for (int i = 1; i < responseStrs.length-1; i++) {
                responseStr += responseStrs[i] + ",";
            }
            responseStr += responseStrs[responseStrs.length-1];
            Gson json = new Gson();
            ParkPort parkPort = json.fromJson(responseStr, ParkPort.class);
            parkPort.getEquByEquId(Integer.valueOf(equId)).setEquStatus("Locked");
            parkPort.setLockedEquNum();
            String parkPortStr = json.toJson(parkPort);
            Document parkPortDoc = MongoDBUtils.updateDocument(parkPortStr);
            MongoDBUtils.updateIntField(DB_NAME, COLLECTION_NAME_PARKPROT,"parkPortId",parkProtId,parkPortDoc);
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
    public String getEquByEquId(@DefaultValue("1001") @QueryParam("equId") int equId){
        String response = MongoDBUtils.findEquByEquId(DB_NAME, COLLECTION_NAME_EQU,equId);
        return response;
    }

    @GET
    @Path("allEquipments")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllEquByParkId(@DefaultValue("1") @QueryParam("parkPortId") int parkPortId){
        String response = MongoDBUtils.findAllEquByParkPort(DB_NAME, COLLECTION_NAME_EQU,parkPortId);
        return response;
    }

    public String getKeyValue(String info) {
        int startIndex = info.indexOf(":");
        int endIndex = info.length();
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
        String result = MongoDBUtils.findEquByEquId(DB_NAME, COLLECTION_NAME_EQU,Integer.valueOf(equId));
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

