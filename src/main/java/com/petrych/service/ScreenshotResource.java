package com.petrych.service;

import com.petrych.db.ScreenshotDao;
import com.petrych.util.Util;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.File;


public class ScreenshotResource {
    @Context
    UriInfo uriInfo;

    @Context
    Request request;

    String id;

    public ScreenshotResource(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }
    
    
    @GET
    @Produces("image/png")
    public Response getScreenshot() {
        Screenshot screenshot = ScreenshotDao.instance.getModel().get(id);
        if (screenshot == null)
            throw new RuntimeException("Get: Screenshot with id " + id +  " not found");
        
        String fileName = screenshot.getName();
        File file = new File(Util.getStorageDir() + File.separatorChar + fileName);
        return Response.ok(file, "image/png").header("Screenshot ", "filename=\"" + file.getName() + "\"")
                .build();
    }
    
}
