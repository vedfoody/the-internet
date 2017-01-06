package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DynamicContentPage extends AbstractPage {

    @FindBy(css = ".large-2 img")
    private List<WebElement> images;

    public DynamicContentPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getImageByIndex(int index) {
        return images.get(index);
    }

    public String getSourceFile(int index) {
        return getImageByIndex(index).getAttribute("src");
    }

    public DynamicContentPage open() {
        driver.get(ROOT_URL + "/dynamic_content");
        return waitForLoading();
    }

    public DynamicContentPage waitForLoading() {
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_FOR_LOADING_ELEMENT);
        wait.until(ExpectedConditions.visibilityOfAllElements(images));
        return this;
    }
}
