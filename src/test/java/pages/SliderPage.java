package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utilities.Sleeper;

/**
 * Created by thuan on 10/01/2017.
 */
public class SliderPage extends AbstractPage {

    @FindBy(css = ".sliderContainer input")
    private WebElement slider;

    @FindBy(id = "range")
    private WebElement sliderValue;

    public SliderPage(WebDriver driver) {
        super(driver);
    }

    public SliderPage open() {
        driver.get(ROOT_URL + "/horizontal_slider");
        return this;
    }

    public SliderPage changeValue(int value) {

        Actions actions = new Actions(driver);
        actions.moveToElement(slider,-slider.getSize().getWidth(),
                slider.getSize().getHeight() / 2).click().perform();
        Sleeper.sleep(2);

        if (!getSliderValue().equals("0"))
            throw new RuntimeException("Can't move slide to 0 position");

        int count = 1;
        while (count <= value * 2) {
            actions.sendKeys(Keys.ARROW_RIGHT).perform();
            Sleeper.sleep(2);
            count++;
        }

        // TODO: 10/01/2017 value property somehow does not change, click on outside of slider to apply the change
        sliderValue.click();

        return this;
    }

    public String getSliderValue() {
        return waitForElementVisible(sliderValue).getText();
    }
}
