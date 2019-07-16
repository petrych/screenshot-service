package com.petrych.exception;

import com.petrych.db.DBAccessor.DBStatus;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class ScreenshotServiceExceptionHandler implements ExceptionMapper<ScreenshotServiceException> {


    @Override
    public Response toResponse(ScreenshotServiceException exception) {

        DBStatus dbStatus = exception.getDbStatus();
        String exceptionMessage = exception.getMessage();

        if (dbStatus == null) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(exceptionMessage).build();
        } else if (dbStatus.equals(DBStatus.NO_CONTENT)) {
            return Response.status(dbStatus.getStatusCode()).build();
        } else {
            return Response.status(dbStatus.getStatusCode()).entity(exceptionMessage).build();
        }
    }
}
