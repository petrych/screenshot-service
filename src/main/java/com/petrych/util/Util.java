package com.petrych.util;

import com.petrych.db.ScreenshotGateway;
import com.petrych.exception.ScreenshotServiceException;
import org.apache.commons.validator.routines.UrlValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/*
This class uses Chrome Driver for Selenium and Chrome browser of the operating system.
chromedriver executable needs to be in the project folder.
The version of Chrome Driver must correspond to the Chrome browser installed.
Download from https://sites.google.com/a/chromium.org/chromedriver/downloads.
 */
public class Util {

    // Directory with Chromedriver
    private static final String TOOLS_DIR = "tools";
    private static final String IMAGE_FORMAT_NAME = "png";

    private static UrlValidator urlValidatorDefault = new UrlValidator();

    private Util() {
    }

    public static String saveScreenshot(ScreenshotGateway screenshotGateway, String urlString) throws ScreenshotServiceException {
        WebDriver driver = getWebDriver(urlString);
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);

        String fileName = createFileName(urlString);
        String fileNameWithFormat = createFilePath(screenshotGateway, fileName) + "." + IMAGE_FORMAT_NAME;
        File file = new File(fileNameWithFormat);
        try {
            ImageIO.write(screenshot.getImage(), IMAGE_FORMAT_NAME, file);
        } catch (IOException e) {
            String message = String.format("Couldn't write a file '%s'", fileNameWithFormat);
            throw new ScreenshotServiceException(message, e);
        }

        driver.quit();

        return fileName;
    }


    private static WebDriver getWebDriver(String urlString) {
        System.setProperty("webdriver.chrome.driver", TOOLS_DIR + File.separatorChar + "chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get(urlString);
        new WebDriverWait(driver, 15);

        return driver;
    }


    public static String createFilePath(ScreenshotGateway sg, String fileName) {
        String dirName = sg.getScreenshotStorageDir();

        return dirName + File.separatorChar + fileName;
    }


    public static String createFileName(String urlString) {
        String fileName = urlString.replaceFirst("^(http[s]?://www\\.|http[s]?://|www\\.)", "");
        fileName = fileName.replaceAll("\\W|_", "-");
        fileName = fileName.replaceAll("-{2,}", "-");
        fileName = fileName.replaceAll("-$", "");

        return fileName;
    }

    public static boolean isUrlValid(String urlString) {

        return urlValidatorDefault.isValid(urlString);
    }
}
