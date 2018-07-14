package pages;

import enums.CanvasProperty;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utilities.Wait.waitForElementVisible;

/**
 * Created by thuan on 10/01/2017.
 */
public class ChallengingDOMPage extends AbstractPage {

    @FindBy(id = "canvas")
    private WebElement canvas;

    public ChallengingDOMPage(WebDriver driver) {
        super(driver);
    }

    public ChallengingDOMPage open() {
        driver.get(ROOT_URL + "/challenging_dom");
        return this;
    }

    public String getCanvasProperty(CanvasProperty property){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        Object value = executor.executeScript("return arguments[0].getContext('2d')." + property.toString(),
                waitForElementVisible(canvas));

        return value.toString();
    }
}
