package utilities;

import com.google.common.base.Predicate;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import variables.GlobalVariables;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;
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

    public static WebElement waitForElementVisible(WebElement element) {
        org.openqa.selenium.support.ui.Wait<WebElement> wait = new FluentWait<WebElement>(element);

        return wait.until(e -> isElementVisible(e) ? e : null);
    }

    public static WebElement waitForElementVisible(SearchContext context, By locator) {
        org.openqa.selenium.support.ui.Wait<SearchContext> wait = new FluentWait<>(context)
                .withTimeout(TIME_OUT_FOR_LOADING_ELEMENT, TimeUnit.SECONDS);

        return wait.until(currentContext -> {
            WebElement element = currentContext.findElement(locator);
            return isElementVisible(element) ? element : null;
        });
    }

    public static List<WebElement> waitForAllElementsVisible(SearchContext context, By locator) {
        org.openqa.selenium.support.ui.Wait<SearchContext> wait = new FluentWait<>(context)
                .withTimeout(TIME_OUT_FOR_LOADING_ELEMENT, TimeUnit.SECONDS);

        return wait.until(currentContext -> {
            List<WebElement> elements = currentContext.findElements(locator);
            for (WebElement element : elements) {
                if (!isElementVisible(element))
                    return null;
            }

            return elements.isEmpty() ? null : elements;
        });
    }

    public static List<WebElement> waitForAllElementsVisible(List<WebElement> elements) {
        org.openqa.selenium.support.ui.Wait<List<WebElement>> wait = new FluentWait<>(elements)
                .withTimeout(TIME_OUT_FOR_LOADING_ELEMENT, TimeUnit.SECONDS);

        return wait.until(searchElements -> {
            for (WebElement element : searchElements) {
                if (!isElementVisible(element))
                    return null;
            }

            return searchElements.isEmpty() ? null : searchElements;
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

    private static boolean isElementVisible(WebElement element) {
        return element.isDisplayed();
    }
}
