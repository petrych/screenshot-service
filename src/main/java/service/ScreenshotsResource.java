package service;

import db.ScreenshotDao;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


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

    // Returns the list of screenshots to the user in the browser
    @GET
    @Produces(MediaType.TEXT_XML)
    public List<Screenshot> getScreenshotsBrowser() {
        List<Screenshot> screenshots = new ArrayList<Screenshot>();
        screenshots.addAll(ScreenshotDao.instance.getModel().values());
        return screenshots;
    }
    

    // Returns the number of screenshots
    // Use http://localhost:8080/screenshots/count
    // to get the total number of records
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        int count = ScreenshotDao.instance.getModel().size();
        return String.valueOf(count);
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newScreenshot(@FormParam("id") String id,
                        @FormParam("name") String name,
                        @Context HttpServletResponse servletResponse) throws IOException {
    
        String fileName = Util.createFileName(name);
        Screenshot screenshot = new Screenshot(id, fileName);
        
        ScreenshotDao.instance.getModel().put(id, screenshot);
        
        servletResponse.sendRedirect("../create_screenshot.html");
    }

    // Defines that the next path parameter after screenshots is
    // treated as a parameter and passed to the ScreenshotResources
    // Allows to type http://localhost:8080/screenshots/1
    // 1 will be treaded as parameter screenshot and passed to ScreenshotResource
    @Path("{screenshot}")
    public ScreenshotResource getScreenshot(@PathParam("screenshot") String id) {
        return new ScreenshotResource(uriInfo, request, id);
    }
    
}
