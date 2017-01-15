package utilities;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.TimeoutException;
import variables.GlobalVariables;

import java.io.File;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static pages.AbstractPage.TIME_OUT_FOR_DOWNLOAD_FILE;

/**
 * Created by thuan on 11/01/2017.
 */
public class Wait {
    public static void waitForDownloadFile(String fileName) throws InterruptedException {
        File testFolder = GlobalVariables.TEST_FOLDER;
        File[] files = testFolder.listFiles();

        int actualWaitTime = 1;
        while(actualWaitTime <= TIME_OUT_FOR_DOWNLOAD_FILE) {

            if(isDownloaded(new File(Paths.get(testFolder.toString(), fileName).toString())) && !hasPartExtension(files))
                return;

            System.out.println("Waiting for download files ... " + actualWaitTime + "s");
            Sleeper.sleep(1);

            files = testFolder.listFiles();
            actualWaitTime += 1;
        }
        throw new TimeoutException("Can't download file named " + fileName);
    }

    private static boolean hasPartExtension(File[] files) {
        return Stream.of(files).anyMatch(file -> FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("part"));
    }

    private static boolean isDownloaded(File file) {
        return file.exists() && !file.isDirectory();
    }
}
