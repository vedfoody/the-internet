package tests.Abstract;

import listeners.MyTestListener;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import variables.GlobalVariables;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;


@Listeners(MyTestListener.class)

public class AbstractTest {

    protected WebDriver driver;

    private File testFolder = GlobalVariables.TEST_FOLDER;

    public final WebDriver getDriver() {
        return driver;
    }

    @Parameters("browser")
    @BeforeClass(alwaysRun = true, groups = "init")
    protected void setup(@Optional("firefox") String browser) throws IOException {
        if(browser.equals("firefox"))
            getFirefoxDriver();
        else if(browser.equals("chrome"))
            getChromeDriver();

        initENV();
    }

    @AfterClass(alwaysRun = true)
    protected void tearDown() throws IOException {
        driver.quit();
        deleteTestFolder();
    }

    protected void initENV() throws IOException {
        // test folder
        testFolder.mkdir();
    }

    private void getChromeDriver(){
        // initialize chrome driver
    }

    private void getFirefoxDriver() {
        System.out.println("The test is using firefox browser");

//        FirefoxBinary binaryFile = new FirefoxBinary(new File(System.getProperty("binaryFile")));
        FirefoxBinary binaryFile = new FirefoxBinary(new File(System.getenv("binaryFile")));


        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.BROWSER, Level.SEVERE);

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);

        driver = new FirefoxDriver(binaryFile, getFireFoxProfile(), capabilities);
    }

    private FirefoxProfile getFireFoxProfile() {
        FirefoxProfile profile = new FirefoxProfile();

        profile.setPreference("browser.download.dir", testFolder.toString());
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "application/octet-stream,images/jpeg,application/pdf");
        profile.setPreference("pdfjs.disabled", true);
        profile.setPreference("browser.download.manager.showAlertOnComplete", false);

        profile.setPreference("geo.prompt.testing", true);
        profile.setPreference("geo.prompt.testing.allow", true);
        profile.setPreference("geo.wifi.uri", "file://" + GlobalVariables.GEOLOCATION_FILE.toString());

        return profile;
    }

    private void deleteTestFolder() throws IOException {
        FileUtils.deleteDirectory(testFolder);
    }
}
