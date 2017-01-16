package pages.fragments;

import enums.SortType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.AbstractFragment;

import java.util.List;
import java.util.stream.Collectors;

import static utilities.Wait.waitForAllElementsVisible;
import static utilities.Wait.waitForElementVisible;

/**
 * Created by thuan on 16/01/2017.
 */
public class SortTable extends AbstractFragment {

    public SortTable(WebDriver driver, WebElement root) {
        super(driver, root);
    }

    public SortTable sortCol(String colHeader, SortType type) {
        for (int i = 0; i < 3; i++) {
            if (getSortType(colHeader) != type)
                getHeader(colHeader).click();
        }

        return this;
    }

    public List<String> getColValues(int index) {
        return getColsByIndex(index).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    private WebElement getHeader(String header) {
        return getHeaders().stream().filter(e -> header.equals(e.getText())).findFirst().get();
    }

    private List<WebElement> getHeaders() {
        return waitForElementVisible(driver, root).findElements(By.cssSelector("thead th"));
    }

    private List<WebElement> getColsByIndex(int index) {
        return waitForAllElementsVisible(driver, By.cssSelector("tbody tr td:nth-child(" + index + ")"), root);
    }

    private SortType getSortType(String colHeader) {
        String foundHeaderClass = getHeader(colHeader).getAttribute("class");
        if (foundHeaderClass.contains("headerSortUp"))
            return SortType.UP;
        else if (foundHeaderClass.contains("headerSortDown"))
            return SortType.DOWN;

        return SortType.NOT_SORTED;
    }
}

