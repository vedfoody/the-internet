package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.fragments.SortTable;
import utilities.Wait;

import static utilities.Wait.waitForElementVisible;

/**
 * Created by thuan on 14/01/2017.
 */
public class DataTablesPage extends AbstractPage {

    @FindBy(id = "table1")
    private WebElement firstTable;

    @FindBy(id = "table2")
    private WebElement secondTable;

    public DataTablesPage(WebDriver driver) {
        super(driver);
    }

    public DataTablesPage open() {
        driver.get(ROOT_URL + "/tables");
        return this;
    }

    public SortTable getFirstTable() {
        return new SortTable(driver, waitForElementVisible(firstTable));
    }

    public SortTable getSecondTable() {
        return new SortTable(driver, waitForElementVisible(secondTable));
    }

    private WebElement getTable(int index) {
        // this should be used instead of using @FindBy
        // the values on table are change continuously when performing any action.
        return Wait.waitForElementVisible(driver, By.id("table" + index));
    }
}



