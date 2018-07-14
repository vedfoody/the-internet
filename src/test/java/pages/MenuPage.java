package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Sleeper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static utilities.Wait.waitForElementVisible;

/**
 * Created by thuan on 11/01/2017.
 */
public class MenuPage extends AbstractPage {

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    public MenuPage open() {
        driver.get(ROOT_URL + "/jqueryui/menu");

        return this;
    }

    public MenuPage backToJQueryUI() {
        waitForLoading().openMenu(Arrays.asList("Enabled", "Back to JQuery UI"));

        return this;
    }

    public MenuPage waitForLoading() {
        waitForElementVisible(driver, By.cssSelector(".example h3"));
        return this;
    }

    public MenuPage downloadPDF() {
        waitForLoading().openMenu(Arrays.asList("Enabled", "Downloads", "PDF"));
        return this;
    }

    private void openMenu(List<String> menuItems) {
        for (int i = 0; i < menuItems.size(); i++) {
            String findingItem = menuItems.get(i);
            WebElement foundElement = getVisibleMenuElements().stream()
                    .filter(e -> {
                        if (getFirstChildIfAny(e, "a").isEmpty())
                            return false;

                        return findingItem.equals(getFirstChildIfAny(e, "a").get(0).getText());
                    })
                    .findFirst()
                    .get();

            hoverOnElement(foundElement);

            if (i == menuItems.size() - 1)
                foundElement.click();
        }
    }

    private WebElement hoverOnElement(WebElement element) {
        if (!element.isDisplayed())
            throw new RuntimeException("element is not visible to hover on");

        new Actions(driver).moveToElement(element).perform();
        Sleeper.sleep(2);

        return element;
    }

    private List<WebElement> getVisibleMenuElements() {
        return driver.findElements(By.cssSelector("#menu li")).stream()
                .filter(WebElement::isDisplayed).collect(Collectors.toList());
    }

    private List<WebElement> getFirstChildIfAny(WebElement parent, String tagName) {
        return parent.findElements(
                By.cssSelector("." + parent.getAttribute("class").replaceAll(" ", ".") + " > " + tagName));
    }
}
