package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by thuan on 16/01/2017.
 */
public class AbstractFragment extends AbstractPage {

    protected WebElement root;

    protected AbstractFragment(WebDriver driver, WebElement root) {
        super(driver);
        this.root = root;
    }
}
