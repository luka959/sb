package steps;

import io.qameta.allure.Step;
import pages.TakeCreditPage;

public class TakeCreditStep {
    @Step("Открываемая страница загружена")
    public void waitUntilPageLoaded() {
        new TakeCreditPage().waitUntilPageLoaded();
    }

    @Step("На странице присутсвуют заголоки {0}")
    public void checkHeaders(String... headers) {
        TakeCreditPage page = new TakeCreditPage();
        for (String header : headers) {
            page.checkIsElementPresence(header);
        }
    }

    @Step("Скроллим страницу")
    public void scroll() {
        new TakeCreditPage().scroll();
        BaseStep.takeScreenshot();
    }

    @Step("Нажимаем на сумму и вводим \"{0}\"")
    public void editSum(String sum) {
        new TakeCreditPage().editSum(sum);
    }

    @Step("Вводим \"Срок кредита\" = \"{0}\"")
    public void editTerm(String term) {
        new TakeCreditPage().editTerm(term);
    }

    @Step("Ставим галочку в чекбокс \"{0}\"")
    public void clickCheckbox(String name) {
        new TakeCreditPage().clickCheckbox(name);
    }

    @Step("Проверка поля \"Размер кредита\" = \"{0}\"")
    public void assertAmount(String amount) {
        new TakeCreditPage().assertAmount(amount);
    }

    @Step("Проверка поля \"Срок кредита\" = \"{0}\"")
    public void assertTerm(String term) {
        new TakeCreditPage().assertTerm(term);
    }

    @Step("Клик на поле \"Срок кредита\"")
    public void clickOnTerm() {
        new TakeCreditPage().clickOnTerm();
    }

    @Step("Отчистка поля \"Срок кредита\"")
    public void deleteTermValue() {
        new TakeCreditPage().deleteTermValue();
    }

    @Step("Проверка поля \"Ставка кредита\" = \"{0}\"")
    public void assertRate(String rate) {
        new TakeCreditPage().assertRate(rate);
    }
}
