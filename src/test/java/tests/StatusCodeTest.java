package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.StatusCodePage;
import tests.Abstract.AbstractTest;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

/**
 * Created by thuan on 21/01/2017.
 */
public class StatusCodeTest extends AbstractTest {

    @Test(groups = "status-code")
    public void getStatusCode() throws IOException {
        PageFactory.initElements(driver, StatusCodePage.class).open();
        assertEquals(PageFactory.initElements(driver, StatusCodePage.class).open()
                .accessStatusCodeLink("404"), 404);
    }
}
