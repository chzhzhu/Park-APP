package com.oracle.hackson.webapp.java.main;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import com.oracle.hackson.webapp.simulation.Equipment;
import com.oracle.hackson.webapp.simulation.ParkPort;
import org.bson.Document;

@Path("parkport")
public class EuqEventResource {
    private String DB_NAME = "ParkingApp";
    private String COLLECTION_NAME = "parkport";

    @POST
    @Path("unlock")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String unlockEqu(String equInfo){
        String equId = getEquId(equInfo);
        Document document = getDocument(equInfo);
        if(validateEqu(equId, "unlock")){
            MongoDBUtils.update(DB_NAME,COLLECTION_NAME,"equId",equId,document);
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
        if(validateEqu(equId, "lock")){
            MongoDBUtils.update(DB_NAME,COLLECTION_NAME,"equId",equId,document);
            return "Lock successfully";
        }else{
            return "Lock Failed";
        }
    }

    private String getEquId(String equInfo) {
        String[] strings = equInfo.split(",");
        String equId = strings[0].substring(7,21);
        return equId;
    }

    private Document getDocument(String equInfo){
        String[] strings = equInfo.split(",");
        String jsonStr = "{"+strings[1];
        BasicDBObject dbObject = (BasicDBObject)JSON.parse(jsonStr);
        Document document = new Document(dbObject.toMap());
        return document;
    }

    private boolean validateEqu(String equId, String event) {
        boolean status = false;
        String result = MongoDBUtils.findByField(DB_NAME,COLLECTION_NAME,"equId",equId);
        Gson json = new Gson();
        ParkPort parkPort = json.fromJson(result,ParkPort.class);
        if(result == null) {
            return status;
        } else {
            for (Equipment e : parkPort.equ) {
                if(e.isAlive() && e.getEquStatus() != event) {
                    status = true;
                }
            }
        }
        return status;
    }
}

