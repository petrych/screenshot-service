package com.petrych.service;

import com.petrych.db.LocalScreenshotGateway;
import com.petrych.db.ScreenshotGateway;
import com.petrych.exception.ScreenshotServiceException;
import com.petrych.util.FileUtil;
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

    private static final Logger LOGGER = LogManager.getLogger(ScreenshotResource.class);

    private ScreenshotGateway screenshotGateway = new LocalScreenshotGateway();


    public ScreenshotResource(UriInfo uriInfo, Request request, String id) {

        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }


    @GET
    @Produces("image/png")
    public Response getScreenshot() throws ScreenshotServiceException {

        Screenshot screenshot = screenshotGateway.getScreenshotById(id);
        if (screenshot == null) {
            LOGGER.debug("Id {} not found", String.valueOf(id));
            return Response.status(Status.NOT_FOUND).build();
        }

        String screenshotName = screenshot.getName();
        boolean fileExists = FileUtil.fileExists(FileUtil.getStorageDir(screenshotGateway), screenshotName);

        if (fileExists) {
            File file = new File(FileUtil.getStorageDir(screenshotGateway) + File.separatorChar + screenshotName);
            return Response.ok(file, "image/png").build();
        } else {
            LOGGER.debug("Screenshot '{}' not found", screenshotName);
            return Response.status(Status.NOT_FOUND).build();
        }
    }

}
