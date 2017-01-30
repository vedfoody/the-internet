package tests;

import org.apache.http.HttpResponse;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.FileDownloadPage;
import pages.FileUploadPage;
import pages.SecureDownloadPage;
import tests.Abstract.AbstractTest;
import variables.GlobalVariables;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by thuan on 21/01/2017.
 */
public class DownloadTests extends AbstractTest {

    @Test(groups = "secure-download")
    public void secureDownload() throws IOException {
        SecureDownloadPage downloadPage = PageFactory.initElements(driver, SecureDownloadPage.class);
        String link = downloadPage
                .openWithBaiscAuth(GlobalVariables.USERNAME, GlobalVariables.PASSWORD).getDownloadLink("snip.PNG");

        HttpResponse response = downloadPage.getDownloadLinkData(link);
        assertEquals(response.getFirstHeader("Content-Length").getValue(), "4637");
        assertEquals(response.getFirstHeader("Content-Type").getValue(), "application/octet-stream");
    }

    @Test(groups = {"download-file"})
    public void downloadTextFile() throws InterruptedException {
        String fileName = "Read Me.txt";
        FileDownloadPage downloadPage = PageFactory.initElements(driver, FileDownloadPage.class);

        assertTrue(downloadPage.open().download(fileName).getDownloadedFileContent(fileName).contains("You can " +
                "import *selection.json* back to the IcoMoon app using the *Import Icons* button (or via Main Menu → " +
                "Manage Projects) to retrieve your icon selection."), "The content of downloaded file is not correct");
    }

    @Test(groups = "upload-file")
    public void uploadTextFile() throws IOException {
        FileUploadPage uploadPage = PageFactory.initElements(driver, FileUploadPage.class);

        String uploadFile = "upload_file.txt";
        uploadPage.open().uploadFile(Files.createFile(Paths.get(GlobalVariables.TEST_FOLDER.toString(), uploadFile)));
        uploadPage.checkUploadedFile(uploadFile);
    }
}
