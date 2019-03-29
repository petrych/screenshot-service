package service;

import java.util.HashMap;
import java.util.Map;


public enum ScreenshotDao {
    instance;

    private Map<String, Screenshot> contentProvider = new HashMap<>();

    
    private ScreenshotDao() {

        Screenshot screenshot = new Screenshot("1","google-com.png");
        contentProvider.put(screenshot.getId(), screenshot);
        
        screenshot = new Screenshot("2", "stackoverflow-com.png");
        contentProvider.put(screenshot.getId(), screenshot);

    }
    
    
    public Map<String, Screenshot> getModel(){
        return contentProvider;
    }
}
