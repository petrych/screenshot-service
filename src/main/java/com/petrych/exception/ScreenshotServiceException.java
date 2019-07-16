package com.petrych.exception;

import com.petrych.db.DBAccessor.DBStatus;


public class ScreenshotServiceException extends Exception {

    private DBStatus dbStatus;

    public DBStatus getDbStatus() {
        return this.dbStatus;
    }

    public ScreenshotServiceException(String message, Exception e) {

        super(message, e);
    }


    public ScreenshotServiceException(String message, DBStatus dbStatus) {

        super(message);
        this.dbStatus = dbStatus;
    }

}
