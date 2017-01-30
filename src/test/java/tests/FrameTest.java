package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FramePage;
import tests.Abstract.AbstractTest;

/**
 * Created by thuan on 21/01/2017.
 */
public class FrameTest extends AbstractTest {

    @Test(groups = "frame")
    public void jumpIntoNestedFrame() throws InterruptedException {
        FramePage framePage = PageFactory.initElements(driver, FramePage.class);
        Assert.assertEquals(framePage.open().goToNestedFramePage().jumpIntoTopFrame().jumpIntoLeftFrame().getBodyText
                (), "LEFT");
    }

    @Test(groups = "frame")
    public void jumpIntoIFrame() {
        FramePage framePage = PageFactory.initElements(driver, FramePage.class);
        Assert.assertEquals(framePage.open().goToIframePage().jumpIntoIframe().getBodyText(),
                "Your content goes here.");
    }
}
