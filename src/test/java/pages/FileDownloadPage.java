package pages;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import variables.GlobalVariables;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileDownloadPage extends AbstractPage {

    private File testFolder = GlobalVariables.TEST_FOLDER;

    public FileDownloadPage(WebDriver driver) {
        super(driver);
    }

    public FileDownloadPage open() {
        driver.get(ROOT_URL + "/download");
        return this;
    }

    private WebElement getDownloadLink(String fileName) {
        List<WebElement> links = driver.findElements(By.cssSelector(".example a"));
        return links.stream()
                .filter(e -> fileName.equals(e.getText())).findFirst().get();
    }

    public FileDownloadPage download(String fileName) throws InterruptedException {
        getDownloadLink(fileName).click();
        waitForDownloadFile(fileName);

        return this;
    }

    public String getDownloadedFileContent(String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get(testFolder.getPath(), fileName))) {
            return stream.collect(Collectors.joining());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file named " + fileName);
        }
    }

    private void waitForDownloadFile(String fileName) throws InterruptedException {
        File[] files = testFolder.listFiles();

        while(hasPartExtension(files) && isDownloaded(files, fileName)) {
            System.out.println("Waiting for download files");
            Thread.sleep(1000);

            files = GlobalVariables.TEST_FOLDER.listFiles();
        }
    }

    private boolean hasPartExtension(File[] files) {
        return Stream.of(files).anyMatch(file -> FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("part"));
    }

    private boolean isDownloaded(File[] files, String fileName) {
        return Stream.of(files).anyMatch(file -> file.getName().equals(fileName));
    }



}
