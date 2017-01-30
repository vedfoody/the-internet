package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;

import static utilities.Wait.isElementPresent;
import static utilities.Wait.waitForElementVisible;

public class ExitIntentPage extends AbstractPage {

    public ExitIntentPage(WebDriver driver) {
        super(driver);
    }

    public ExitIntentPage open() {
        driver.get(ROOT_URL + "/exit_intent");
        return this;
    }

    public ExitIntentPage moveToOutOfViewport() throws AWTException, InterruptedException {
        WebElement header = waitForElementVisible(driver.findElement(By.cssSelector(".example h3")));
        Robot robot = new Robot();
        robot.mouseMove(header.getLocation().getX(), header.getLocation().getY());
        Thread.sleep(2000);
        robot.mouseMove(0, 0);
        return this;
    }

    public boolean isModalDialogVisible() {
        return isElementPresent(driver, By.className("modal"));
    }

}
