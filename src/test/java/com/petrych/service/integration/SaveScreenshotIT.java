package com.petrych.service.integration;

import com.petrych.db.ScreenshotGateway;
import com.petrych.service.TestingEndpoints;
import com.petrych.service.TestingScreenshotGateway;
import com.petrych.util.FileUtil;
import com.petrych.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SaveScreenshotIT {

    // Directory with "production" screenshots
    // TODO - instead use a test directory for tests and rewrite tests which use Util.saveScreenshot(..)
    private static final String STORAGE_DIR = TestingEndpoints.STORAGE_DIR.toString();
    private static final String URL_REAL = "https://www.apple.com/";

    private ScreenshotGateway screenshotGateway = new TestingScreenshotGateway();

    @Test
    public void saveScreenshot() throws Exception {
        String fileName = Util.saveScreenshot(screenshotGateway, URL_REAL);

        assertFalse(fileName.isEmpty(), "File name of a created screenshot is empty.");
        assertTrue(FileUtil.fileExists(STORAGE_DIR, fileName), "Screenshot with the given name does not exist.");

        // Clean up after the test
        FileUtil.removeFile(STORAGE_DIR, fileName);
        assertFalse(FileUtil.fileExists(STORAGE_DIR, fileName), "Could not remove the screenshot from the folder.");
    }
}
