package pages;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {
    public final static String ROOT_URL = "https://the-internet.herokuapp.com";

    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }
}
