package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

abstract class AbstractPage {

    final static int TIME_OUT_FOR_LOADING_ELEMENT = 90;
    final static int TIME_OUT_FOR_DOWNLOAD_FILE = 90;

    final static String ROOT_URL = "https://the-internet.herokuapp.com";

    WebDriver driver;

    AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected WebElement waitForElementVisible(WebElement element) {
        return new WebDriverWait(driver, TIME_OUT_FOR_LOADING_ELEMENT)
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForElementVisible(By locator, SearchContext context) {
        return new WebDriverWait(driver, TIME_OUT_FOR_LOADING_ELEMENT)
                .until(ExpectedConditions.visibilityOf(context.findElement(locator)));
    }

    protected List<WebElement> waitForElementsVisible(By locator, SearchContext context) {
        return new WebDriverWait(driver, TIME_OUT_FOR_LOADING_ELEMENT)
                .until(ExpectedConditions.visibilityOfAllElements(context.findElements(locator)));
    }

    WebElement waitForElementPresent(By locator) {
        return new WebDriverWait(driver, TIME_OUT_FOR_LOADING_ELEMENT)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
