import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import service.Util;

import static org.junit.jupiter.api.Assertions.*;

public class UtilTest {

    private static final String URL_ONE = "https://github.blog/2019-06-26-noops-week-§±!@#$%^&*()-_=+[]{};:'|\n,<?";
    private static final String URL_TWO = "https://stackoverflow.com/questions/115983/";
    private static final String URL_THREE = "www.site.com";


    @ParameterizedTest
    @ValueSource(strings = { URL_ONE, URL_TWO, URL_THREE })
    public void createFileName(String argument) {
        String fileName = Util.createFileName(argument);
        System.out.println(argument);

        // File name doesn't start with "http(s)" and "www"
        assertFalse(fileName.matches("^(http[s]?://www\\.|http[s]?://|www\\.).*"));
        // File name consists only of letters, numbers and hyphens
        assertTrue(fileName.matches("^[0-9A-Za-z-]+$"));
        // File name ends with a letter or a number
        assertTrue(fileName.matches("^.*(\\w|\\d)$"));
    }
}
