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
        return this;
    }

    public FramePage goToNestedFramePage() throws InterruptedException {
        getLinks().stream()
                .filter(e -> e.getText().equals("Nested Frames"))
                .findFirst()
                .get()
                .findElement(By.tagName("a")).click();

        return this;
    }

    public FramePage jumpToLeftFrame() {
        driver.switchTo().frame(waitForElementVisible(driver.findElement(By.cssSelector("frame[src='/frame_left']"))));
        return this;
    }

    public FramePage jumpToTopFrame() {
        driver.switchTo().frame(waitForElementVisible(driver.findElement(By.cssSelector("frame[src='/frame_top']"))));
        return this;
    }

    public String getBodyText() {
        return waitForElementVisible(driver.findElement(By.tagName("body"))).getText();
    }

    private List<WebElement> getLinks() {
        return waitForElementVisible(driver.findElement(By.className("example"))).findElements(By.tagName("li"));
    }
}
