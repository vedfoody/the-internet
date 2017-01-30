package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utilities.Wait.waitForAllElementsVisible;
import static utilities.Wait.waitForElementVisible;

/**
 * Created by thuan on 12/01/2017.
 */
public class JavaScriptAlertPage extends AbstractPage {

    @FindBy(id = "result")
    private WebElement result;

    public JavaScriptAlertPage(WebDriver driver) {
        super(driver);
    }

    public JavaScriptAlertPage open() {
        driver.get(ROOT_URL + "/javascript_alerts");
        return this;
    }

    public Alert openJSAlert() {
        getButtonByCaption("Click for JS Alert").click();
        return driver.switchTo().alert();
    }

    public Alert openJSConfirmAlert() {
        getButtonByCaption("Click for JS Confirm").click();
        return driver.switchTo().alert();
    }

    public Alert openJSPromptAlert() {
        getButtonByCaption("Click for JS Prompt").click();
        return driver.switchTo().alert();
    }

    public String getResult() {
        return waitForElementVisible(result).getText();
    }

    private WebElement getButtonByCaption(String caption) {
        return waitForAllElementsVisible(driver, By.cssSelector(".example button"))
                .stream().filter(e -> e.getText().equals(caption)).findFirst().get();
    }
}
