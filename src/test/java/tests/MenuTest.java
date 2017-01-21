package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MenuPage;
import utilities.Wait;

/**
 * Created by thuan on 21/01/2017.
 */
public class MenuTest extends AbstractTest {

    @Test(dependsOnGroups = "init", groups = "menu")
    public void openMenu() {
        PageFactory.initElements(driver, MenuPage.class).open().backToJQueryUI().waitForLoading();
        Assert.assertFalse(driver.getCurrentUrl().contains("/jqueryui/menu"));
    }

    @Test(dependsOnGroups = "init", groups = "menu")
    public void downloadPDF() throws InterruptedException {
        PageFactory.initElements(driver, MenuPage.class).open().downloadPDF();
        // already check file existence in wait method
        // read pdf file should be handled later :(
        Wait.waitForDownloadFile("menu.pdf");
    }
}
