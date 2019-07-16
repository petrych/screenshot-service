package com.petrych.db;

import com.petrych.exception.ScreenshotServiceException;
import com.petrych.service.Screenshot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;


public class DBAccessor {

    private static final Logger LOGGER = LogManager.getLogger(DBAccessor.class);


    private DBAccessor() {
    }


    public enum DBStatus {

        INTERNAL_DB_ERROR(500, "Database can not be accessed"),
        NO_CONTENT(204, "Database has no items"),
        OK(200, "Database status is OK");

        private final int statusCode;
        private final String message;

        DBStatus(int statusCode, String message) {

            this.statusCode = statusCode;
            this.message = message;
        }

        public String getMessage() {

            return this.message;
        }

        public int getStatusCode() {

            return this.statusCode;
        }
    }


    public static Map<String, Screenshot> getAllScreenshots() throws ScreenshotServiceException {

        Map<String, Screenshot> screenshotsProvider = ScreenshotDao.instance.getModel();
        if (screenshotsProvider == null) {
            String message = DBStatus.INTERNAL_DB_ERROR.getMessage();
            LOGGER.fatal(message);
            throw new ScreenshotServiceException(message, DBStatus.INTERNAL_DB_ERROR);
        } else if (screenshotsProvider.isEmpty()) {
            String message = DBStatus.NO_CONTENT.getMessage();
            LOGGER.warn(message);
            throw new ScreenshotServiceException(message, DBStatus.NO_CONTENT);
        } else {
            return screenshotsProvider;
        }
    }


    /*
        Returns null if screenshot with the given @id does not exist
     */
    public static Screenshot getScreenshotById(String id) throws ScreenshotServiceException {

        Map<String, Screenshot> screenshotsProvider = getAllScreenshots();
        if (screenshotsProvider.containsKey(id)) {
            return screenshotsProvider.get(id);
        } else {
            return null;
        }
    }


    public static int getScreenshotsCount() throws ScreenshotServiceException {

        return getAllScreenshots().size();
    }

}