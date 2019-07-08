package com.petrych.service;

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

    // Directory with screenshots
    private static final String STORAGE_DIR = "storage";
    // Directory with Chromedriver
    private static final String TOOLS_DIR = "tools";
    private static final String IMAGE_FORMAT_NAME = "png";

    private Util() {
        throw new IllegalStateException("Instantiating utility class.");
    }

  public static String saveScreenshot(String urlString) {
      System.setProperty("webdriver.chrome.driver", TOOLS_DIR + File.separatorChar + "chromedriver");
      WebDriver driver = new ChromeDriver();
      driver.get(urlString);
      new WebDriverWait(driver, 15);
      Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);

      String fileName = createFileName(urlString);
      String fileNameWithFormat = createFilePath(fileName) + "." + IMAGE_FORMAT_NAME;
      File file = new File(fileNameWithFormat);
      try {
          ImageIO.write(screenshot.getImage(), IMAGE_FORMAT_NAME, file);
      } catch (IOException e) {
          // TODO - Should the exception really be caught? Or thrown further?
          e.printStackTrace();
      }

      driver.quit();
      
      return fileName;
  }
  
  
  public static String createFilePath(String fileName) {
    String dirName = getStorageDir();
    File dir = new File(dirName);
    if (!dir.exists()) dir.mkdirs();
    
    return dirName + File.separatorChar + fileName;
  }
  
  
  public static String createFileName(String urlString) {
    String fileName = urlString.replaceFirst("^(http[s]?://www\\.|http[s]?://|www\\.)","");
    fileName = fileName.replaceAll("\\W|_", "-");
    fileName = fileName.replaceAll("-{2,}", "-");
    fileName = fileName.replaceAll("-$", "");

    return fileName;
  }

  public static final String getStorageDir() {
      return STORAGE_DIR;
  }
  
}
