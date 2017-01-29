package tests;

import email.MyImap;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.ForgotPasswordPage;
import tests.Abstract.AbstractTest;
import variables.GlobalVariables;

import javax.mail.MessagingException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by thuan on 21/01/2017.
 */
public class EmailTest extends AbstractTest {

    @Test(groups = "forgot-password")
    public void sendForgotPasswordEmail() throws MessagingException, InterruptedException {
        Date now = Calendar.getInstance().getTime();

        System.out.println("Current time is: " + now.toString());
        PageFactory.initElements(driver, ForgotPasswordPage.class).open().retrievePassword(GlobalVariables.TEST_EMAIL);

        new MyImap(GlobalVariables.HOST, GlobalVariables.EMAIL, GlobalVariables.EMAIL_PASSWORD)
                .getMessageWithRetry(now, 10);
    }
}
