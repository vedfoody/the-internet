package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.ExitIntentPage;
import tests.Abstract.AbstractTest;

import java.awt.*;

import static org.testng.Assert.assertTrue;

/**
 * Created by thuan on 21/01/2017.
 */
public class MouseInteractionTest extends AbstractTest {

    @Test(groups = {"exit-intent"})
    public void checkMovingMouseToOutOfViewport() throws AWTException, InterruptedException {
        ExitIntentPage page = PageFactory.initElements(driver, ExitIntentPage.class);
        page.open().moveToOutOfViewport();
        assertTrue(page.isModalDialogVisible(),
                "The modal dialog is not displayed after moving mouse out of viewport");
    }
}
