package com.petrych.exception;

import com.petrych.db.DbStatus;


public class ScreenshotServiceException extends Exception {

    private DbStatus dbStatus;

    public DbStatus getDbStatus() {
        return this.dbStatus;
    }

    public ScreenshotServiceException(String message, Exception e) {

        super(message, e);
    }


    public ScreenshotServiceException(String message, DbStatus dbStatus) {

        super(message);
        this.dbStatus = dbStatus;
    }

}
