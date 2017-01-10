package tests;

import email.MyImap;
import entities.Location;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utilities.Sleeper;
import variables.GlobalVariables;

import javax.mail.MessagingException;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

public class MavenTest extends AbstractTest {

    @Test(dependsOnGroups = "init", groups = "slider")
    public void changeSliderValue() {
        Assert.assertEquals(PageFactory.initElements(driver, SliderPage.class)
                .open().changeValue(5).getSliderValue(), "5", "Slider value is not correct");
    }

    @Test(dependsOnGroups = "init", groups = "geolocation")
    public void getGeolocation() {
        GeolocationPage geolocationPage = PageFactory.initElements(driver, GeolocationPage.class);
        Assert.assertTrue(geolocationPage.open().getLocation().equals(new Location(10f, 106f)));
    }

    @Test(dependsOnGroups = "init", groups = "frame")
    public void jumpIntoNestedFrame() throws InterruptedException {
        FramePage framePage = PageFactory.initElements(driver, FramePage.class);
        Assert.assertEquals(framePage.open().goToNestedFramePage().jumpIntoTopFrame().jumpIntoLeftFrame().getBodyText(), "LEFT");
    }

    @Test(dependsOnGroups = "init", groups = "frame")
    public void jumpIntoIFrame() {
        FramePage framePage = PageFactory.initElements(driver, FramePage.class);
        Assert.assertEquals(framePage.open().goToIframePage().jumpIntoIframe().getBodyText(),
                "Your content goes here.");
    }

    @Test(dependsOnGroups = "init", groups = "forgot-password")
    public void sendForgotPasswordEmail() throws MessagingException, InterruptedException {
        Date now = Calendar.getInstance().getTime();

        System.out.println("Current time is: " + now.toString());
        PageFactory.initElements(driver, ForgotPasswordPage.class).open().retrievePassword(GlobalVariables.TEST_EMAIL);

        new MyImap(GlobalVariables.HOST, GlobalVariables.USERNAME, GlobalVariables.PASSWORD)
                .getMessageWithRetry(now, 10);
    }

    @Test(dependsOnGroups = "init", groups = "upload-file")
    public void uploadTextFile() throws IOException {
        FileUploadPage uploadPage = PageFactory.initElements(driver, FileUploadPage.class);

        String uploadFile = "upload_file.txt";
        uploadPage.open().uploadFile(Files.createFile(Paths.get(GlobalVariables.TEST_FOLDER.toString(), uploadFile)));
        uploadPage.checkUploadedFile(uploadFile);
    }

    @Test(dependsOnGroups = "init", groups = {"download-file"})
    public void downloadTextFile() throws InterruptedException {
        String fileName = "Read Me.txt";
        FileDownloadPage downloadPage = PageFactory.initElements(driver, FileDownloadPage.class);

        Assert.assertTrue(downloadPage.open().download(fileName).getDownloadedFileContent(fileName).contains("You can " +
                "import *selection.json* back to the IcoMoon app using the *Import Icons* button (or via Main Menu → " +
                "Manage Projects) to retrieve your icon selection."), "The content of downloaded file is not correct");
    }

    @Test(groups = {"dynamic-content"})
    public void checkDynamicIMG() {
        System.out.println("checkDynamicIMG");
        DynamicContentPage page = PageFactory.initElements(driver, DynamicContentPage.class);
        String imgBeforeRefresh = page.open().getSourceFile(0);
        Assert.assertFalse(page.open().getSourceFile(0).equals(imgBeforeRefresh),
                "The img is not changed after refresh ");
    }

    @Test(groups = {"dynamic-controls"})
    public void checkDynamicControls() {
        DynamicControlsPage page = PageFactory.initElements(driver, DynamicControlsPage.class);
        Assert.assertTrue(page.open().isCheckboxDisplayed(), "The checkbox is not displayed after opening page");
        Assert.assertFalse(page.removeCheckbox().waitLoadingProgress().isCheckboxDisplayed(),
                "The checkbox has not been removed");
    }

    @Test(groups = {"dynamically-loaded"})
    public void checkDynamicLoading() {
        Assert.assertEquals(PageFactory.initElements(driver, DynamicallyLoadedPage.class).open().openExample(1)
                .startWaitingProcess().getFinishedText(), "Hello World!");
    }

    @Test(groups = {"exit-intent"})
    public void checkMovingMouseToOutOfViewport() throws AWTException, InterruptedException {
        ExitIntentPage page = PageFactory.initElements(driver, ExitIntentPage.class);
        page.open().moveToOutOfViewport();
        Assert.assertTrue(page.isModalDialogVisible(),
                "The modal dialog is not displayed after moving mouse out of viewport");
    }

    @Test(enabled = false)
    public void canvasTest() throws InterruptedException {
//        driver.get("https://the-internet.herokuapp.com/challenging_dom");
//        Thread.sleep(5000);
//
//        JavascriptExecutor executor = (JavascriptExecutor) driver;
//        System.out.println(executor.executeScript("return arguments[0].getContext('2d')",
//                driver.findElement(By.id("canvas"))));
    }


    @Test(enabled = false)
    public void testInteractionOnContextMenu() throws InterruptedException {
//        driver.get("https://the-internet.herokuapp.com/context_menu");
//        Actions action = new Actions(driver);
//        action.contextClick(new WebDriverWait(driver, 5)
//                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("hot-spot"))))).sendKeys("t")
//                .build().perform();
//        Thread.sleep(3000);
//        new WebDriverWait(driver, 5).until(ExpectedConditions.alertIsPresent()).accept();
//        Thread.sleep(3000);
    }
}
