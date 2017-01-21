package variables;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by thuan on 04/01/2017.
 */
public class GlobalVariables {
    public final static File TEST_FOLDER = new File(Paths.get(System.getProperty("user.home"), "test_download").toString());
    public final static String TEST_EMAIL = "thuankhuat.learning@gmail.com";

    // email
    public final static  String HOST = "imap.gmail.com";
    public final static  String EMAIL = "thuankhuat.learning@gmail.com";
    public final static  String EMAIL_PASSWORD = "123456789!@#"; // TODO: 06/01/2017 should be passed on runtime
    public final static String SENDER = "no-reply@the-internet.herokuapp.com";
    public final static String FORGOT_PWD_SUBJECT = "Forgot Password from the-internet";

    // geolocation
    public final static Path GEOLOCATION_FILE = Paths.get(GlobalVariables.TEST_FOLDER.toString(), "geolocation.txt");

    // login
    public final static String USERNAME = "admin";
    public final static String PASSWORD = "admin";

    // log files
//    public final static File LOG_FOLDER = new File(Paths.get(System.getProperty("user.home"), "selenium_log").toString());
    public final static File LOG_FOLDER = new File(System.getProperty("maven.project.build.directory", "./target/"));

}
