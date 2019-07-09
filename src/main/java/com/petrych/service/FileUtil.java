package com.petrych.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class FileUtil {
  
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
  
    private FileUtil() {
        String errorMessage = "Instantiating utility class is not allowed.";
        logger.error(errorMessage);
        throw new IllegalStateException(errorMessage);
    }

    
    public static boolean fileExists(String dirName, String fileName) {
        File[] dirContent = getDirContent(dirName);
        for (File file : dirContent) {
            if (file.getName().contains(fileName)) {
                return true;
            }
        }
        logger.debug("File '{}' does not exist in the directory '{}'.", fileName, dirName);

        return false;
    }


    public static void removeFile(String dirName, String fileName) throws IOException
    {
        File[] dirContent = getDirContent(dirName);
        for (File file : dirContent) {
            if (file.getName().contains(fileName)) {
                Files.delete(file.toPath());
                logger.debug("File '{}' successfully deleted from the directory '{}'.", fileName, dirName);
            }
        }
    }


    public static File[] getDirContent(String dirName) {
        File dir = new File(dirName);
        File[] dirContent = dir.listFiles();
        logger.debug("Successfully retrieved the list of files in the directory '{}'.", dirName);
        
        return dirContent;
    }
}
