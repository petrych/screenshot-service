package com.petrych.db;

import com.petrych.exception.ScreenshotServiceException;
import com.petrych.service.Screenshot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class LocalScreenshotGateway implements ScreenshotGateway {

    private static final Logger LOGGER = LogManager.getLogger(LocalScreenshotGateway.class);
    private Map<String, Screenshot> screenshotsProvider;


    public LocalScreenshotGateway() {
        screenshotsProvider = ScreenshotDao.instance.getModel();
    }


    @Override
    public Map<String, Screenshot> getAllScreenshots() throws ScreenshotServiceException {
        if (screenshotsProvider == null) {
            String message = DbStatus.INTERNAL_DB_ERROR.getMessage();
            LOGGER.fatal(message);
            throw new ScreenshotServiceException(message, DbStatus.INTERNAL_DB_ERROR);
        } else if (screenshotsProvider.isEmpty()) {
            String message = DbStatus.NO_CONTENT.getMessage();
            LOGGER.warn(message);
            throw new ScreenshotServiceException(message, DbStatus.NO_CONTENT);
        } else {
            return screenshotsProvider;
        }
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
