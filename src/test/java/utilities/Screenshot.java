package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import variables.GlobalVariables;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by thuan on 17/01/2017.
 */
public class Screenshot {
    public static void takeScreenShot(WebDriver driver, String name, Class<?> clazz) {
        takeScreenshot(driver, new File(Paths.get(GlobalVariables.LOG_FOLDER.toString(), "Screenshots",
                clazz.getSimpleName(), name + ".png").toString()));
    }

    public static void takeScreenShotOnFailure(WebDriver driver, String name, Class<?> clazz) {
        takeScreenshot(driver, new File(Paths.get(GlobalVariables.LOG_FOLDER.toString(), "Failures",
                clazz.getSimpleName(), name + ".png").toString()));
    }

    private static void takeScreenshot(WebDriver driver, File file) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.forceMkdir(file.getParentFile());
            FileUtils.copyFile(screenshot, file);
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
