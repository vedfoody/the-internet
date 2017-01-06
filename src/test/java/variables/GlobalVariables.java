package variables;

import java.io.File;
import java.nio.file.Paths;

/**
 * Created by thuan on 04/01/2017.
 */
public class GlobalVariables {
    public final static File TEST_FOLDER = new File(Paths.get(System.getProperty("user.home"), "test_download").toString());
    public final static String TEST_EMAIL = "thuankhuat.learning@gmail.com";

    // email
    public final static  String HOST = "imap.gmail.com";
    public final static  String USERNAME = "thuankhuat.learning@gmail.com";
    public final static  String PASSWORD = "123456789!@#"; // TODO: 06/01/2017 should be passed on runtime
    public final static String SENDER = "no-reply@the-internet.herokuapp.com";
    public final static String FORGOT_PWD_SUBJECT = "Forgot Password from the-internet";
}
