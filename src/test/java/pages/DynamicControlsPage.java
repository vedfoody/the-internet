package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class DynamicControlsPage extends AbstractPage {

    public DynamicControlsPage(WebDriver driver) {
        super(driver);
    }

    public DynamicControlsPage removeCheckbox() {
        getActionButton().click();
        return this;
    }

    public DynamicControlsPage waitLoadingProgress() {
        WebElement loadingBar = driver.findElement(By.id("loading"));
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        // a short break to manage timeout right after click on btn
        wait.withTimeout(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOf(loadingBar));

        wait.withTimeout(TIME_OUT_FOR_LOADING_ELEMENT, TimeUnit.SECONDS)
                .until(ExpectedConditions.not(ExpectedConditions.visibilityOf(loadingBar)));
        return this;
    }

    public DynamicControlsPage open() {
        driver.get(ROOT_URL + "/dynamic_controls");
        return this;
    }

    public boolean isCheckboxDisplayed() {
        return isElementPresent(By.id("checkbox"));
    }

    private WebElement getActionButton() {
        return driver.findElement(By.id("btn"));
    }
}
