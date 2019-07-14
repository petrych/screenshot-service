package com.petrych.service;

import com.petrych.db.ScreenshotDao;
import com.petrych.util.FileUtil;
import com.petrych.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import java.io.File;


public class ScreenshotResource {
    @Context
    UriInfo uriInfo;

    @Context
    Request request;

    String id;

    private static final Logger logger = LogManager.getLogger(ScreenshotResource.class);


    public ScreenshotResource(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }


    @GET
    @Produces("image/png")
    public Response getScreenshot() {
        Screenshot screenshot;
        String fileName;
        Response r;

        try {
            screenshot = ScreenshotDao.instance.getModel().get(id);
            fileName = screenshot.getName();
        } catch (NullPointerException e) {
            logger.debug("Id {} does not exist.", String.valueOf(id));
            return Response.status(Status.NOT_FOUND).build();
        }

        boolean fileExists = FileUtil.fileExists(Util.getStorageDir(), fileName);

        if (fileExists) {
            File file = new File(Util.getStorageDir() + File.separatorChar + fileName);
            r = Response.ok(file, "image/png").build();
        } else {
            r = Response.status(Status.NOT_FOUND).build();
        }

        return r;
    }

}
