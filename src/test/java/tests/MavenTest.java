package tests;

import com.google.common.collect.Ordering;
import email.MyImap;
import entities.Location;
import enums.CanvasProperty;
import enums.SortType;
import org.openqa.selenium.Alert;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utilities.Wait;
import variables.GlobalVariables;

import javax.mail.MessagingException;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import static org.testng.Assert.assertTrue;

public class MavenTest extends AbstractTest {

    @Test(dependsOnGroups = "init", groups = "data-table")
    public void sortTable() {
        DataTablesPage dataTablesPage = PageFactory.initElements(driver, DataTablesPage.class);
        dataTablesPage.open();

        assertTrue(Ordering.natural().isOrdered(dataTablesPage.sortFirstTable("Last Name", SortType.DOWN)
                .getFirstTableCol(1)), "Col in table 1 has not been sorted");

        assertTrue(Ordering.natural().reverse().isOrdered(dataTablesPage.sortSecondTable("Last Name", SortType.UP)
                .getSecondTableCol(1)), "Col in table 2 has not been sorted");
    }

    @Test(dependsOnGroups = "init", groups = "js-error")
    public void getJSError() {
        LogEntries entries = PageFactory.initElements(driver, JavaScriptErrorPage.class).open().getLogs();
        Assert.assertEquals(entries.getAll().get(0).getMessage(), "TypeError: document.propertyThatDoesNotExist is undefined");
    }

    @Test(dependsOnGroups = "init", groups = "alert")
    public void openJSAlert() {
        JavaScriptAlertPage alertPage = PageFactory.initElements(driver, JavaScriptAlertPage.class);
        alertPage.open().openJSAlert().accept();
        Assert.assertEquals(alertPage.getResult(), "You successfuly clicked an alert");
    }

    @Test(dependsOnGroups = "init", groups = "alert")
    public void openJSConfirmAlert() {
        JavaScriptAlertPage alertPage = PageFactory.initElements(driver, JavaScriptAlertPage.class);
        alertPage.open().openJSConfirmAlert().accept();
        Assert.assertEquals(alertPage.getResult(),
                "You clicked: Ok");
    }

    @Test(dependsOnGroups = "init", groups = "alert")
    public void openJSPromptAlert() {
        JavaScriptAlertPage alertPage = PageFactory.initElements(driver, JavaScriptAlertPage.class);
        Alert alert = alertPage.open().openJSPromptAlert();
        alert.sendKeys("openJSPromptAlert");
        alert.accept();
        Assert.assertEquals(alertPage.getResult(), "You entered: openJSPromptAlert");
    }

    @Test(dependsOnGroups = "init", groups = "menu")
    public void openMenu() {
        PageFactory.initElements(driver, MenuPage.class).open().backToJQueryUI().waitForLoading();
        Assert.assertFalse(driver.getCurrentUrl().contains("/jqueryui/menu"));
    }

    @Test(dependsOnGroups = "init", groups = "menu")
    public void downloadPDF() throws InterruptedException {
        PageFactory.initElements(driver, MenuPage.class).open().downloadPDF();
        // already check file existence in wait method
        // read pdf file should be handled later :(
        Wait.waitForDownloadFile("menu");
    }

    @Test(dependsOnGroups = "init", groups = "slider")
    public void changeSliderValue() {
        Assert.assertEquals(PageFactory.initElements(driver, SliderPage.class)
                .open().changeValue(5).getSliderValue(), "5", "Slider value is not correct");
    }

    @Test(dependsOnGroups = "init", groups = "geolocation")
    public void getGeolocation() {
        GeolocationPage geolocationPage = PageFactory.initElements(driver, GeolocationPage.class);
        assertTrue(geolocationPage.open().getLocation().equals(new Location(10f, 106f)));
    }

    @Test(dependsOnGroups = "init", groups = "frame")
    public void jumpIntoNestedFrame() throws InterruptedException {
        FramePage framePage = PageFactory.initElements(driver, FramePage.class);
        Assert.assertEquals(framePage.open().goToNestedFramePage().jumpIntoTopFrame().jumpIntoLeftFrame().getBodyText
                (), "LEFT");
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

        assertTrue(downloadPage.open().download(fileName).getDownloadedFileContent(fileName).contains("You can" +
                " " +
                "import *selection.json* back to the IcoMoon app using the *Import Icons* button (or via Main Menu → " +
                "Manage Projects) to retrieve your icon selection."), "The content of downloaded file is not correct");
    }

    @Test(dependsOnGroups = "init", groups = {"dynamic-content"})
    public void checkDynamicIMG() {
        DynamicContentPage page = PageFactory.initElements(driver, DynamicContentPage.class);
        String imgBeforeRefresh = page.open().getSourceFile(0);
        Assert.assertFalse(page.open().getSourceFile(0).equals(imgBeforeRefresh),
                "The img is not changed after refresh ");
    }

    @Test(dependsOnGroups = "init", groups = {"dynamic-controls"})
    public void checkDynamicControls() {
        DynamicControlsPage page = PageFactory.initElements(driver, DynamicControlsPage.class);
        assertTrue(page.open().isCheckboxDisplayed(), "The checkbox is not displayed after opening page");
        Assert.assertFalse(page.removeCheckbox().waitLoadingProgress().isCheckboxDisplayed(),
                "The checkbox has not been removed");
    }

    @Test(dependsOnGroups = "init", groups = {"dynamically-loaded"})
    public void checkDynamicLoading() {
        Assert.assertEquals(PageFactory.initElements(driver, DynamicallyLoadedPage.class).open().openExample(1)
                .startWaitingProcess().getFinishedText(), "Hello World!");
    }

    @Test(dependsOnGroups = "init", groups = {"exit-intent"})
    public void checkMovingMouseToOutOfViewport() throws AWTException, InterruptedException {
        ExitIntentPage page = PageFactory.initElements(driver, ExitIntentPage.class);
        page.open().moveToOutOfViewport();
        assertTrue(page.isModalDialogVisible(),
                "The modal dialog is not displayed after moving mouse out of viewport");
    }

    @Test(dependsOnGroups = "init", groups = {"canvas"})
    public void canvasTest() throws InterruptedException {
        Assert.assertEquals(PageFactory.initElements(driver, ChallengingDOMPage.class)
                .open().getCanvasProperty(CanvasProperty.FONT), "60px Arial");
    }
}
