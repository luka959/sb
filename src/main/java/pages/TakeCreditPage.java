package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TakeCreditPage extends BasePage {
    private static final String CREDIT_CONTAINER = "//div[@class='any-credit']";

    @FindBy(xpath = CREDIT_CONTAINER)
    private WebElement containerFields;

    @FindBy(xpath = CREDIT_CONTAINER + "//div[@id='term-slider']//div[@class='anyui-slider__value']")
    private WebElement termContainer;

    @FindBy(xpath = CREDIT_CONTAINER + "//div[@id='amount-slider']//div[@class='anyui-slider__value']")
    private WebElement amountContainer;

    @FindBy(xpath = CREDIT_CONTAINER + "//div[text()='Ставка']/following-sibling::*")
    private WebElement rateValue;

    public void checkIsElementPresence(String elementText) {
        Assert.assertFalse(
                "Элемент \"" + elementText + "\" не найден",
                driver.findElements(By.xpath("//*[text()='" + elementText + "']")).isEmpty()
        );
    }

    public void waitUntilPageLoaded() {
        new WebDriverWait(driver, 10)
                .until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//div[contains(@class, 'body-container')]/*")
                        )
                );
    }

    public void scroll() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);",
                containerFields
        );
    }

    public void editSum(String sum) {
        amountContainer.click();
        fillField(amountContainer.findElement(By.xpath(".//input")), sum);
    }

    public void clickOnTerm() {
        termContainer.click();
    }

    public void deleteTermValue() {
        WebElement element = termContainer.findElement(By.xpath(".//input"));
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.DELETE);
    }

    public void editTerm(String term) {
        fillField(termContainer.findElement(By.xpath(".//input")), term);
    }

    public void clickCheckbox(String name) {
        containerFields.findElement(By.xpath(".//label[@role='checkbox']//*[text()='" + name + "']")).click();
    }

    public void assertTerm(String term) {
        Assert.assertEquals("Неправильный срок кредита", term, getSliderValue(termContainer));
    }

    public void assertAmount(String amount) {
        Assert.assertEquals("Неправильный размер кредита", amount, getSliderValue(amountContainer));
    }

    public void assertRate(String rate) {
        Assert.assertEquals("Неправильная ставка кредита", rate, rateValue.getText());
    }

    private String getSliderValue(WebElement element) {
        List<WebElement> childElements = element.findElements(
                By.xpath(".//*[self::span or self::input]")
        );
        for (WebElement childElement : childElements) {
            if (childElement.isDisplayed()) {
                String tagName = childElement.getTagName();
                switch (tagName) {
                    case "span":
                        return childElement.getText();
                    case "input":
                        String value = childElement.getAttribute("value");
                        String unit = unQuote(
                                ((JavascriptExecutor) driver)
                                        .executeScript(
                                                "return window.getComputedStyle(arguments[0], ':after')" +
                                                        ".getPropertyValue('content');",
                                                childElement.findElement(By.xpath("./.."))
                                        )
                                        .toString()
                        );
                        return value + unit;
                }
            }
        }
        return "";
    }

    private String unQuote(String source) {
        if (source.length() < 2) {
            return source;
        }
        if (source.charAt(0) == '"' && source.charAt(source.length() - 1) == '"') {
            return source.substring(1, source.length() - 1);
        }
        return source;
    }
}
