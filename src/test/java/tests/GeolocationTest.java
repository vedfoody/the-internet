package tests;

import entities.Location;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.GeolocationPage;

import static org.testng.Assert.assertTrue;

/**
 * Created by thuan on 21/01/2017.
 */
public class GeolocationTest extends AbstractTest {

    @Test(dependsOnGroups = "init", groups = "geolocation")
    public void getGeolocation() {
        GeolocationPage geolocationPage = PageFactory.initElements(driver, GeolocationPage.class);
        assertTrue(geolocationPage.open().getLocation().equals(new Location(10f, 106f)));
    }
}
