package pages;

import entities.Location;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by thuan on 08/01/2017.
 */
public class GeolocationPage extends AbstractPage {

    public GeolocationPage(WebDriver driver) {
        super(driver);
    }

    public GeolocationPage open() {
        driver.get(ROOT_URL + "/geolocation");
        return this;
    }

    public Location getLocation() {
        waitForElementVisible(By.cssSelector(".example button"), driver).click();

        return new Location(Float.parseFloat(getLatitude()), Float.parseFloat(getLongitude()));
    }

    private String getLatitude() {
        return waitForElementVisible(By.id("lat-value"), driver).getText();
    }

    private String getLongitude() {
        return waitForElementVisible(By.id("long-value"), driver).getText();
    }
}
