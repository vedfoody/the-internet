package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utilities.Wait.waitForElementVisible;

/**
 * Created by thuan on 04/01/2017.
 */
public class ForgotPasswordPage extends AbstractPage {

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    public ForgotPasswordPage open() {
        driver.get(ROOT_URL + "/forgot_password");
        return this;
    }

    public void retrievePassword(String email) {
        waitForElementVisible(driver.findElement(By.id("email"))).sendKeys(email);
        waitForElementVisible(driver.findElement(By.id("form_submit"))).click();
    }
}
