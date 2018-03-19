package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import steps.BaseStep;

public abstract class BasePage {

    WebDriver driver = BaseStep.getDriver();

    public BasePage() {
        PageFactory.initElements(driver, this);
    }

    public void fillField(WebElement field, String value) {
        field.clear();
        field.sendKeys(value);
    }

}
