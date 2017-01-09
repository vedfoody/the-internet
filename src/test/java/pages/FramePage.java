package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by thuan on 06/01/2017.
 */
public class FramePage extends AbstractPage {

    public FramePage(WebDriver driver) {
        super(driver);
    }

    public FramePage open() {
        driver.get(ROOT_URL + "/frames");
        waitForElementVisible(By.cssSelector(".example h3"), driver);

        return this;
    }

    public FramePage goToNestedFramePage() throws InterruptedException {
        findLink("Nested Frames").findElement(By.tagName("a")).click();

        return this;
    }

    public FramePage goToIframePage() {
        findLink("iFrame").findElement(By.tagName("a")).click();
        waitForIframeLoading();

        return this;
    }

    public FramePage jumpIntoLeftFrame() {
        driver.switchTo().frame(waitForElementVisible(By.cssSelector("frame[src='/frame_left']"), driver));
        return this;
    }

    public FramePage jumpIntoTopFrame() {
        driver.switchTo().frame(waitForElementVisible(driver.findElement(By.cssSelector("frame[src='/frame_top']"))));
        return this;
    }

    public String getBodyText() {
        return waitForElementVisible(driver.findElement(By.tagName("body"))).getText();
    }

    public FramePage jumpIntoIframe() {
        driver.switchTo().frame("mce_0_ifr");
        return this;
    }

    private List<WebElement> getLinks() {
        return waitForElementsVisible(By.cssSelector(".example li"), driver);
    }

    private WebElement findLink(String linkText) {
        return getLinks().stream()
                .filter(e -> e.getText().equals(linkText))
                .findFirst()
                .get();
    }

    private FramePage waitForIframeLoading() {
        waitForElementVisible(driver.findElement(By.id("mceu_13")));
        return this;
    }
}
