package com.petrych.service;

import com.petrych.db.DbStatus;
import com.petrych.db.LocalScreenshotGateway;
import com.petrych.db.ScreenshotGateway;
import com.petrych.exception.ScreenshotServiceException;
import com.petrych.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import java.io.IOException;
import java.util.Map;


// Maps the resource to the URL screenshots
// http://localhost:8080/screenshots

@Path("/screenshots")
public class ScreenshotsResource {

    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;

    @Context
    Request request;

    private static final Logger logger = LogManager.getLogger(ScreenshotResource.class);

    private ScreenshotGateway screenshotGateway = new LocalScreenshotGateway();

    // Returns the list of screenshots to the user in the browser
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllScreenshots() throws ScreenshotServiceException {

        Map<String, Screenshot> screenshotsProvider = screenshotGateway.getAllScreenshots();
        logger.debug("{}. Total screenshots: {}", DbStatus.OK.getMessage(), screenshotsProvider.size());

        return Response.status(Status.OK).entity(screenshotsProvider).build();
    }


    // Returns the number of screenshots
    // Use http://localhost:8080/screenshots/count
    // to get the total number of records
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getScreenshotsCount() throws ScreenshotServiceException {

        int count = screenshotGateway.getScreenshotsCount();
        logger.debug("Screenshots count: {}", count);

        return Response.status(Status.OK).entity(count).build();
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newScreenshot(@FormParam("id") String id,
                              @FormParam("name") String name,
                              @Context HttpServletResponse servletResponse)
            throws IOException, ScreenshotServiceException {

        String fileName = Util.createFileName(name);
        Screenshot screenshot = new Screenshot(id, fileName);
        screenshotGateway.getAllScreenshots().put(id, screenshot);
        servletResponse.sendRedirect("../create_screenshot.html");
    }

    // Defines that the next path parameter after screenshots is
    // treated as a parameter and passed to the ScreenshotResources
    // Allows to type http://localhost:8080/screenshots/1
    // 1 will be treaded as parameter screenshot and passed to ScreenshotResource
    @Path("{id}")
    public ScreenshotResource getScreenshot(@PathParam("id") String id) {

        return new ScreenshotResource(uriInfo, request, id);
    }

}
