package com.petrych.util;

public class ScreenshotServiceException extends Exception {

    public ScreenshotServiceException(String message) {

        super(message);
    }

    public ScreenshotServiceException(String message, Exception e) {

        super(message, e);
    }
}
