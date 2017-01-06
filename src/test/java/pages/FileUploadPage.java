package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.File;
import java.nio.file.Path;

/**
 * Created by thuan on 04/01/2017.
 */
public class FileUploadPage extends AbstractPage {

    @FindBy(id = "file-submit")
    private WebElement submitButton;

    public FileUploadPage(WebDriver driver) {
        super(driver);
    }

    public FileUploadPage open() {
        driver.get(ROOT_URL + "/upload");
        return this;
    }

    public FileUploadPage uploadFile(Path filePath) {
        waitForElementVisible(driver.findElement(By.id("file-upload"))).sendKeys(filePath.toString());
        waitForElementVisible(submitButton).click();

        return this;
    }

    public void checkUploadedFile(String uploadedFileName) {
        Assert.assertEquals(waitForElementVisible(driver.findElement(By.id("uploaded-files"))).getText(),
                uploadedFileName, uploadedFileName + " is not uploaded");
    }
}
