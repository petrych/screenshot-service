package com.petrych.service;

import java.io.File;

public class FileUtil {

    public static boolean fileExists(String dirName, String fileName) {
        File[] dirContent = getDirContent(dirName);
        for (File file : dirContent) {
            if (file.getName().contains(fileName)) {
                return true;
            }
        }

        return false;
    }


    public static void removeFile(String dirName, String fileName) {
        File[] dirContent = getDirContent(dirName);
        for (File file : dirContent) {
            if (file.getName().contains(fileName)) {
                file.delete();
            }
        }
    }


    public static File[] getDirContent(String dirName) {
        File dir = new File(dirName);

        return dir.listFiles();
    }
}
