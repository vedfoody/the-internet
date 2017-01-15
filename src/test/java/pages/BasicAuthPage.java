package pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by thuan on 15/01/2017.
 */
public class BasicAuthPage extends AbstractPage {

    public BasicAuthPage(WebDriver driver) {
        super(driver);
    }

    public BasicAuthPage open() {
        driver.get(ROOT_URL + "/basic_auth");
        return this;
    }


}
