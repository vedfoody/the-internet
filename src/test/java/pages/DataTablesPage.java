package pages;

import enums.SortType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by thuan on 14/01/2017.
 */
public class DataTablesPage extends AbstractPage {

    public DataTablesPage(WebDriver driver) {
        super(driver);
    }

    public DataTablesPage open() {
        driver.get(ROOT_URL + "/tables");
        return this;
    }

    public DataTablesPage sortFirstTable(String header, SortType type) {
        sortCol(header, type, getTable(1));
        return this;
    }

    public DataTablesPage sortSecondTable(String header, SortType type) {
        sortCol(header, type, getTable(2));
        return this;
    }

    public List<String> getFirstTableCol(int index) {
        return getColsByIndex(index, getTable(1)).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getSecondTableCol(int index) {
        return getColsByIndex(index, getTable(2)).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    private DataTablesPage sortCol(String colHeader, SortType type, WebElement table) {
        for (int i = 0; i < 3; i++) {
            if (getSortType(colHeader, table) != type)
                getHeader(colHeader, table).click();
        }

        return this;
    }

    private SortType getSortType(String colHeader, WebElement table) {
        String foundHeaderClass = getHeader(colHeader, table).getAttribute("class");
        if (foundHeaderClass.contains("headerSortUp"))
            return SortType.UP;
        else if (foundHeaderClass.contains("headerSortDown"))
            return SortType.DOWN;

        return SortType.NOT_SORTED;
    }

    private WebElement getHeader(String header, WebElement table) {
        return getHeaders(table).stream().filter(e -> header.equals(e.getText())).findFirst().get();
    }

    private List<WebElement> getHeaders(WebElement table) {
        return waitForElementVisible(table).findElements(By.cssSelector("thead th"));
    }

    private List<WebElement> getColsByIndex(int index, WebElement table) {
        return waitForAllElementsVisible(By.cssSelector("tbody tr td:nth-child(" + index + ")"), table);
    }

    private WebElement getTable(int index) {
        // this should be used instead of using @FindBy
        // the values on table are change continuously when performing any action.
        return waitForElementVisible(By.id("table" + index), driver);
    }
}


