package com.petrych.db;

import com.petrych.exception.ScreenshotServiceException;
import com.petrych.service.Screenshot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Map;

public class LocalScreenshotGateway implements ScreenshotGateway {

    // Directory with screenshots
    private static final String STORAGE_DIR = "storage";
    private static final Logger LOGGER = LogManager.getLogger(LocalScreenshotGateway.class);
    private Map<String, Screenshot> screenshotsProvider;


    public LocalScreenshotGateway() {
        screenshotsProvider = ScreenshotDao.instance.getModel();
    }


    @Override
    public String getScreenshotStorageDir() {
        File dir = new File(STORAGE_DIR);
        if (!dir.exists()) dir.mkdirs();

        return STORAGE_DIR;
    }


    @Override
    public String checkDbStatus() throws ScreenshotServiceException {
        if (screenshotsProvider == null) {
            String message = DbStatus.INTERNAL_DB_ERROR.getMessage();
            LOGGER.fatal(message);
            throw new ScreenshotServiceException(message, DbStatus.INTERNAL_DB_ERROR);

        } else if (screenshotsProvider.isEmpty()) {
            String message = DbStatus.NO_CONTENT.getMessage();
            LOGGER.warn(message);
            throw new ScreenshotServiceException(message, DbStatus.NO_CONTENT);
        }

        return DbStatus.OK.getMessage();
    }


    @Override
    public Map<String, Screenshot> getAllScreenshots() throws ScreenshotServiceException {
        checkDbStatus();
        return screenshotsProvider;
    }


    /*
        Returns null if screenshot with the given @id does not exist
     */
    @Override
    public Screenshot getScreenshotById(String id) throws ScreenshotServiceException {

        Map<String, Screenshot> screenshotsProvider = getAllScreenshots();
        if (screenshotsProvider.containsKey(id)) {
            return screenshotsProvider.get(id);
        } else {
            return null;
        }
    }


    @Override
    public int getScreenshotsCount() throws ScreenshotServiceException {

        return getAllScreenshots().size();
    }
}
