package tests;

import com.google.gson.Gson;
import entities.Geolocation;
import entities.Location;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import variables.GlobalVariables;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

class AbstractTest {

    protected WebDriver driver;

    private File testFolder = GlobalVariables.TEST_FOLDER;

    @Test(groups = "init")
    protected void setup() throws IOException {
        initializeDriver();
        initENV();
    }

    @AfterClass(alwaysRun = true)
    protected void tearDown() throws IOException {
        driver.quit();
        deleteTestFolder();
    }

    private void initializeDriver() {
        getFirefoxBrowser();
    }

    private void initENV() throws IOException {
        // test folder
        testFolder.mkdir();

        //geolocation
        simulateGeoLocationProvider();

    }

    private void getFirefoxBrowser() {
        System.out.println("The test is using firefox browser");

        // TODO: 01/01/2017 get user home folder
        FirefoxBinary binaryFile = new FirefoxBinary(new File("/home/thuan/Downloads/firefox-46/firefox"));
        driver = new FirefoxDriver(binaryFile, getFireFoxProfile());
    }

    private FirefoxProfile getFireFoxProfile() {
        FirefoxProfile profile = new FirefoxProfile();

        profile.setPreference("browser.download.dir", testFolder.toString());
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "application/octet-stream,images/jpeg,application/pdf");
        profile.setPreference("pdfjs.disabled", true);
        profile.setPreference("browser.download.manager.showAlertOnComplete", true);

        profile.setPreference("geo.prompt.testing", true);
        profile.setPreference("geo.prompt.testing.allow", true);
        profile.setPreference("geo.wifi.uri", "file://" + GlobalVariables.GEOLOCATION_FILE.toString());

        return profile;
    }

    private void deleteTestFolder() throws IOException {
        FileUtils.deleteDirectory(testFolder);
    }

    private void simulateGeoLocationProvider() throws IOException {
        String content = new Gson().toJson(new Geolocation("OK", 10.0f, new Location(10f, 106f)));

        Files.createFile(GlobalVariables.GEOLOCATION_FILE);
        Files.write(GlobalVariables.GEOLOCATION_FILE, content.getBytes());
    }
}
