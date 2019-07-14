package com.petrych.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;


public class Test {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        WebTarget service = client.target(getBaseURI());

        // Get all the Screenshots, total 2
        System.out.println("===== Get all the Screenshots, total 2");
        System.out.println(service.path("screenshots").request().accept(MediaType.TEXT_XML).get(String.class));

        //Create a screenshot with id 3
        Form form = new Form();
        form.param("id", "3");
        form.param("name", "www.yahoo.com");
        service.path("screenshots").request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);

        //Get all the screenshots, id 3 should have been created
        System.out.println("\n======= Get all the screenshots, total 3, id 3 should have been created");
        System.out.println(service.path("screenshots").request().accept(MediaType.TEXT_XML).get(String.class));
    }


    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080").build();
    }

}