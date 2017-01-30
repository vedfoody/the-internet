package pages;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

import static utilities.Wait.waitForAllElementsVisible;

/**
 * Created by thuan on 16/01/2017.
 */
public class StatusCodePage extends AbstractPage {

    public StatusCodePage(WebDriver driver) {
        super(driver);
    }

    public StatusCodePage open() {
        driver.get(ROOT_URL + "/status_codes");
        return this;
    }

    public int accessStatusCodeLink(String code) throws IOException {
        String target = getLinks().stream().filter(e -> code.equals(e.getText()))
                .map(e -> e.getAttribute("href")).findFirst().get();
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            return client.execute(new HttpGet(target)).getStatusLine().getStatusCode();
        }
    }

    private List<WebElement> getLinks() {
        return waitForAllElementsVisible(driver, By.cssSelector(".example li a"));
    }
}
