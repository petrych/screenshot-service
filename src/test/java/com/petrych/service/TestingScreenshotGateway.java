package com.petrych.service;

import com.petrych.db.LocalScreenshotGateway;


public class TestingScreenshotGateway extends LocalScreenshotGateway {

    @Override
    public String getScreenshotStorageDir() {
        return TestingEndpoints.STORAGE_DIR.toString();
    }
}
