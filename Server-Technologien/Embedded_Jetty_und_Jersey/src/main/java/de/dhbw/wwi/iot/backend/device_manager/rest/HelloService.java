/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package de.dhbw.wwi.iot.backend.device_manager.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author dennis
 */
@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloService {
    
    @GET
    public Message sayHello() {
        return new Message("Hello, JSON!");
    }
    
}
