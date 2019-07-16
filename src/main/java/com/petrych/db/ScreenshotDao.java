package com.petrych.db;

import com.petrych.service.Screenshot;

import java.util.Map;

/*
 * The Data Access Object provides access to an underlying persistence storage.
 *
 * Acts as a database, is a folder in the project root directory.
 */
public enum ScreenshotDao {

    instance;

    private Map<String, Screenshot> contentProvider = null;//new HashMap<>();


    ScreenshotDao() {

        Screenshot screenshot = new Screenshot("1", "google-com.png");
        contentProvider.put(screenshot.getId(), screenshot);

        screenshot = new Screenshot("2", "stackoverflow-com.png");
        contentProvider.put(screenshot.getId(), screenshot);

        screenshot = new Screenshot("3", "gmail-com.png");
        contentProvider.put(screenshot.getId(), screenshot);

        screenshot = new Screenshot("4", "apple-com.png");
        contentProvider.put(screenshot.getId(), screenshot);
    }


    protected Map<String, Screenshot> getModel() {

        return contentProvider;
    }
}
