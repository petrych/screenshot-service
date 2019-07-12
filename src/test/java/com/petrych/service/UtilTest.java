package com.petrych.service;

import com.petrych.util.Util;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class UtilTest {

    private static final String URL_REAL = "https://www.apple.com/";
    private static final String URL_TEST_TWO = "https://stackoverflow.com/questions/115983/-§±!@#$%^&*()-_=+[]{};:'|\n,<?";
    private static final String URL_TEST_THREE = "www.site.com";


    @ParameterizedTest
    @ValueSource(strings = { URL_REAL, URL_TEST_TWO, URL_TEST_THREE })
    public void createFileName(String argument) {
        String fileName = Util.createFileName(argument);

        // File name doesn't start with "http(s)" and "www"
        assertFalse(fileName.matches("^(http[s]?://www\\.|http[s]?://|www\\.).*"));
        // File name consists only of letters, numbers and hyphens
        assertTrue(fileName.matches("^[0-9A-Za-z-]+$"));
        // File name ends with a letter or a number
        assertTrue(fileName.matches("^.*(\\w|\\d)$"));
    }
}
