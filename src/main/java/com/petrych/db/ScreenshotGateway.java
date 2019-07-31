package com.petrych.db;

import com.petrych.exception.ScreenshotServiceException;
import com.petrych.service.Screenshot;

import java.util.Map;


public interface ScreenshotGateway {

    Map<String, Screenshot> getAllScreenshots() throws ScreenshotServiceException;

    Screenshot getScreenshotById(String id) throws ScreenshotServiceException;

    int getScreenshotsCount() throws ScreenshotServiceException;

}
