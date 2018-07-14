package tests;

import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.JavaScriptErrorPage;
import tests.Abstract.AbstractTest;

public class JsErrorTest extends AbstractTest {

    @Test(groups = "js-error")
    public void getJSError() {
        LogEntries entries = PageFactory.initElements(driver, JavaScriptErrorPage.class).open().getLogs();
        Assert.assertEquals(entries.getAll().get(0).getMessage(),
                "TypeError: document.propertyThatDoesNotExist is undefined");
    }

}
