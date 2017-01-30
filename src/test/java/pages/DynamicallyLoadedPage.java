package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utilities.Wait.waitForElementPresent;
import static utilities.Wait.waitForElementVisible;

public class DynamicallyLoadedPage extends AbstractPage {

    @FindBy(css = "#start button")
    private WebElement startButton;

    public DynamicallyLoadedPage(WebDriver driver) {
        super(driver);
    }

    public DynamicallyLoadedPage open() {
        driver.get(ROOT_URL + "/dynamic_loading");
        return this;
    }

    public DynamicallyLoadedPage openExample(int index) {
        driver.findElement(By.cssSelector("a[href='/dynamic_loading/" + String.valueOf(index) + "']")).click();
        return this;
    }

    public DynamicallyLoadedPage startWaitingProcess() {
        waitForElementVisible(startButton).click();
        return this;
    }

    public String getFinishedText() {
        return waitForElementPresent(driver, By.cssSelector("#finish[style=''] h4")).getText();
    }

}
