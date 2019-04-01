package com.oracle.hackson.webapp.java.main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello")
public class HelloWorldResource {

    public static final String CLICHED_MESSAGE = "Jersey Start:";
    public String kfkStr;
    public long kfkOffset;

    @GET
    @Produces("text/plain")
    public String getHello() {
        return CLICHED_MESSAGE;
    }
}
