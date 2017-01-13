package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;

/**
 * Created by thuan on 12/01/2017.
 */
public class JavaScriptErrorPage extends AbstractPage {

    public JavaScriptErrorPage(WebDriver driver) {
        super(driver);
    }

    public JavaScriptErrorPage open() {
        driver.get(ROOT_URL + "/javascript_error");

        return this;
    }

    public LogEntries getLogs() {
        return driver.manage().logs().get(LogType.BROWSER);
    }
}
