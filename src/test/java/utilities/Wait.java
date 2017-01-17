package utilities;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import variables.GlobalVariables;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by thuan on 11/01/2017.
 */
public class Wait {
    public final static int TIME_OUT_FOR_LOADING_ELEMENT = 90;
    public final static int TIME_OUT_FOR_DOWNLOAD_FILE = 90;

    public static void waitForDownloadFile(String fileName) throws InterruptedException {
        File testFolder = GlobalVariables.TEST_FOLDER;
        File[] files = testFolder.listFiles();

        int actualWaitTime = 1;
        while (actualWaitTime <= TIME_OUT_FOR_DOWNLOAD_FILE) {

            if (isDownloaded(new File(Paths.get(testFolder.toString(), fileName).toString())) && !hasPartExtension
                    (files))
                return;

            System.out.println("Waiting for download files ... " + actualWaitTime + "s");
            Sleeper.sleep(1);

            files = testFolder.listFiles();
            actualWaitTime += 1;
        }
        throw new TimeoutException("Can't download file named " + fileName);
    }

    public static boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static WebElement waitForElementVisible(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, TIME_OUT_FOR_LOADING_ELEMENT)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForElementVisible(WebDriver driver, By locator, SearchContext context) {
        return new WebDriverWait(driver, TIME_OUT_FOR_LOADING_ELEMENT)
                .until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver input) {
                        return context.findElement(locator);
                    }
                });
    }

    public static List<WebElement> waitForAllElementsVisible(WebDriver driver, By locator, SearchContext context) {
        return new WebDriverWait(driver, TIME_OUT_FOR_LOADING_ELEMENT)
                .until(new ExpectedCondition<List<WebElement>>() {
                    @Override
                    public List<WebElement> apply(WebDriver input) {
                        return context.findElements(locator);
                    }
                });
    }

    public static WebElement waitForElementPresent(WebDriver driver, By locator) {
        return new WebDriverWait(driver, TIME_OUT_FOR_LOADING_ELEMENT)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private static boolean hasPartExtension(File[] files) {
        return Stream.of(files).anyMatch(file -> FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("part"));
    }

    private static boolean isDownloaded(File file) {
        return file.exists() && !file.isDirectory();
    }
}
