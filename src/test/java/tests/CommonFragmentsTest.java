package tests;

import com.google.common.collect.Ordering;
import enums.CanvasProperty;
import enums.SortType;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import tests.Abstract.AbstractTest;

import static org.testng.Assert.assertTrue;

/**
 * Created by thuan on 21/01/2017.
 */
public class CommonFragmentsTest extends AbstractTest {

    @Test(groups = "data-table")
    public void sortTable() {
        DataTablesPage dataTablesPage = PageFactory.initElements(driver, DataTablesPage.class).open();

        assertTrue(Ordering.natural().isOrdered(
                dataTablesPage.getFirstTable()
                        .sortCol("Last Name", SortType.DOWN)
                        .getColValues(1)),
                "Col in table 1 has not been sorted");

        assertTrue(Ordering.natural().reverse().isOrdered(
                dataTablesPage.getSecondTable()
                        .sortCol("Last Name", SortType.UP)
                        .getColValues(1)),
                "Col in table 2 has not been sorted");
    }

    @Test(groups = "slider")
    public void changeSliderValue() {
        Assert.assertEquals(PageFactory.initElements(driver, SliderPage.class)
                .open().changeValue(5).getSliderValue(), "5", "Slider value is not correct");
    }

    @Test(groups = {"canvas"})
    public void canvasTest() throws InterruptedException {
        Assert.assertEquals(PageFactory.initElements(driver, ChallengingDOMPage.class)
                .open().getCanvasProperty(CanvasProperty.FONT), "60px Arial");
    }

    @Test(groups = {"dynamic-content"})
    public void checkDynamicIMG() {
        DynamicContentPage page = PageFactory.initElements(driver, DynamicContentPage.class);
        String imgBeforeRefresh = page.open().getSourceFile(0);
        Assert.assertFalse(page.open().getSourceFile(0).equals(imgBeforeRefresh),
                "The img is not changed after refresh ");
    }

    @Test(groups = {"dynamic-controls"})
    public void checkDynamicControls() {
        DynamicControlsPage page = PageFactory.initElements(driver, DynamicControlsPage.class);
        assertTrue(page.open().isCheckboxDisplayed(), "The checkbox is not displayed after opening page");
        Assert.assertFalse(page.removeCheckbox().waitLoadingProgress().isCheckboxDisplayed(),
                "The checkbox has not been removed");
    }

    @Test(groups = {"dynamically-loaded"})
    public void checkDynamicLoading() {
        Assert.assertEquals(PageFactory.initElements(driver, DynamicallyLoadedPage.class).open().openExample(1)
                .startWaitingProcess().getFinishedText(), "Hello World!");
    }

}
