package pages;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.List;

/**
 * Created by thuan on 15/01/2017.
 */
public class SecureDownloadPage extends AbstractPage {

    @FindBy(css = ".example a")
    List<WebElement> links;

    public SecureDownloadPage(WebDriver driver) {
        super(driver);
    }

    public SecureDownloadPage open() {
        driver.get(ROOT_URL + "/download_secure");
        return this;
    }

    public SecureDownloadPage openWithBaiscAuth(String username, String pass) {
        driver.get(String.format("http://%s:%s@the-internet.herokuapp.com/download_secure", username, pass));
        return this;
    }

    public String getDownloadLink(String fileName) {
        return links.stream().filter(e -> fileName.equals(e.getText())).findFirst().get().getAttribute("href");
    }

    public HttpResponse getDownloadLinkData(String url) throws IOException {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            return client.execute(new HttpHead(url));
        }
    }
}
