package steps;

import io.qameta.allure.Step;
import pages.MainPage;

public class MainPageStep {

    @Step("выбран пункт меню \"{0}\"")
    public void selectMenuItem(String menuItem) {
        new MainPage().selectMenuItem(menuItem);
    }

    @Step("выбран подпункт меню \"{0}\"")
    public void selectSubmenuItem(String submenuItem) {
        new MainPage().selectSubmenuItem(submenuItem);
    }
}
