package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.JavaScriptAlertPage;
import tests.Abstract.AbstractTest;

/**
 * Created by thuan on 21/01/2017.
 */
public class JsAlertTest extends AbstractTest {

    @Test(groups = "alert")
    public void openJSAlert() {
        JavaScriptAlertPage alertPage = PageFactory.initElements(driver, JavaScriptAlertPage.class);
        alertPage.open().openJSAlert().accept();
        Assert.assertEquals(alertPage.getResult(), "You successfuly clicked an alert");
    }

    @Test(groups = "alert")
    public void openJSConfirmAlert() {
        JavaScriptAlertPage alertPage = PageFactory.initElements(driver, JavaScriptAlertPage.class);
        alertPage.open().openJSConfirmAlert().accept();
        Assert.assertEquals(alertPage.getResult(),
                "You clicked: Ok");
    }

    @Test(groups = "alert")
    public void openJSPromptAlert() {
        JavaScriptAlertPage alertPage = PageFactory.initElements(driver, JavaScriptAlertPage.class);
        Alert alert = alertPage.open().openJSPromptAlert();
        alert.sendKeys("openJSPromptAlert");
        alert.accept();
        Assert.assertEquals(alertPage.getResult(), "You entered: openJSPromptAlert");
    }

}
