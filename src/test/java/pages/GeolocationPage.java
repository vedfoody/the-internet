package pages;

import entities.Location;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utilities.Wait.waitForElementVisible;

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
        waitForElementVisible(driver, By.cssSelector(".example button")).click();

        return new Location(Float.parseFloat(getLatitude()), Float.parseFloat(getLongitude()));
    }

    private String getLatitude() {
        return waitForElementVisible(driver, By.id("lat-value")).getText();
    }

    private String getLongitude() {
        return waitForElementVisible(driver, By.id("long-value")).getText();
    }
}

