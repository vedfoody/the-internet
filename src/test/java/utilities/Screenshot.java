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
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File realScreenshot = new File(Paths.get(GlobalVariables.LOG_FOLDER.toString(),
                    clazz.getSimpleName(), name + ".png").toString());

            FileUtils.forceMkdir(realScreenshot.getParentFile());
            FileUtils.copyFile(screenshot, realScreenshot);
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
