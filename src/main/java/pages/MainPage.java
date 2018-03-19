package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {
    //контейнер горизонтального меню, содержащий "Взять кредит", "Выбрать карту" и т.д.
    @FindBy(xpath = "//div[contains(@class, 'header-container')]//ul[@class='alt-menu-mid__list']")
    public WebElement menuContainer;

    public void selectMenuItem(String menuItem) {
        new Actions(driver).moveToElement(menuContainer
                .findElement(By.xpath(".//a[contains(normalize-space(@aria-label), '" + menuItem + "')]"))
        ).build().perform();
    }

    public void selectSubmenuItem(String submenuItem) {
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOf(
                        menuContainer.findElement(By.xpath(".//a[text()='" + submenuItem + "']"))
                ))
                .click();
    }
}
