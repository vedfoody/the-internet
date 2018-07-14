package tests;

import com.google.gson.Gson;
import entities.Geolocation;
import entities.Location;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.GeolocationPage;
import tests.Abstract.AbstractTest;
import variables.GlobalVariables;

import java.io.IOException;
import java.nio.file.Files;

import static org.testng.Assert.assertTrue;

/**
 * Created by thuan on 21/01/2017.
 */
public class GeolocationTest extends AbstractTest {

    @Override
    protected void initENV() throws IOException {
        super.initENV();

        simulateGeoLocationProvider();
    }

    @Test(groups = "geolocation")
    public void getGeolocation() {
        GeolocationPage geolocationPage = PageFactory.initElements(driver, GeolocationPage.class);
        assertTrue(geolocationPage.open().getLocation().equals(new Location(10f, 106f)));
    }

    private void simulateGeoLocationProvider() throws IOException {
        String content = new Gson().toJson(new Geolocation("OK", 10.0f, new Location(10f, 106f)));

        Files.createFile(GlobalVariables.GEOLOCATION_FILE);
        Files.write(GlobalVariables.GEOLOCATION_FILE, content.getBytes());
    }
}
