package service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
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
  public static String saveScreenshot(String urlString) {
      //
      // Installation
      System.setProperty("webdriver.chrome.driver", "chromedriver");
      WebDriver driver = new ChromeDriver();
      driver.get(urlString);
      new WebDriverWait(driver, 15);
      ru.yandex.qatools.ashot.Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);

      String fileName = createFileName(urlString); // new
      String filePath = createFilePath(fileName) + ".png";
      File file = new File(filePath);
      try {
          ImageIO.write(screenshot.getImage(), "PNG", file);
      } catch (IOException e) {
          e.printStackTrace();
      }

      driver.close();
      
      return fileName;
  }
  
  
  public static String createFilePath(String fileName) {
    String dirName = "screenshots";
    File dir = new File(dirName);
    if (!dir.exists()) dir.mkdirs();
    
    return dirName + File.separatorChar + fileName;
  }
  
  
  public static String createFileName(String urlString) {
    urlString = urlString.replaceFirst("^(http[s]?://www\\.|http[s]?://|www\\.)","");
    urlString = urlString.replaceAll("/$", "");
    urlString = urlString.replaceAll("\\W", "-");
    
    return urlString;
  }
  
}
