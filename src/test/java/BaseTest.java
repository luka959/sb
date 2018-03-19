import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import steps.BaseStep;
import steps.MainPageStep;
import steps.TakeCreditStep;

public class BaseTest extends BaseStep {
    MainPageStep mainPageStep = new MainPageStep();
    TakeCreditStep takeCreditStep = new TakeCreditStep();

    @DisplayName("Взять кредит")
    @Test
    public void testTakeCredit() {
        mainPageStep.selectMenuItem("Взять кредит");
        mainPageStep.selectSubmenuItem("Кредит на любые цели");
        takeCreditStep.waitUntilPageLoaded();
        takeCreditStep.scroll();
        takeCreditStep.checkHeaders("Возьмите кредит на любые цели", "Рассчитать кредит");
        takeCreditStep.assertAmount("45 000");
        takeCreditStep.assertTerm("1 год");
        takeCreditStep.assertRate("13,9%");
        takeCreditStep.editSum("259000");
        takeCreditStep.clickOnTerm();
        takeCreditStep.assertTerm("12 мес");
        takeCreditStep.editTerm("59");
        takeCreditStep.clickCheckbox("Получаю зарплату или пенсию в Сбербанке");
        takeCreditStep.assertTerm("4 года 11 мес");
        takeCreditStep.assertRate("12,9%");
        takeScreenshot();
    }

    @DisplayName("Взять кредит с ошибкой")
    @Test
    public void testTakeCreditWithError() {
        mainPageStep.selectMenuItem("Взять кредит");
        mainPageStep.selectSubmenuItem("Кредит на любые цели");
        takeCreditStep.waitUntilPageLoaded();
        takeCreditStep.scroll();
        takeCreditStep.checkHeaders("Возьмите кредит на любые цели", "Рассчитать кредит");
        takeCreditStep.assertAmount("45 000");
        takeCreditStep.assertTerm("1 год");
        takeCreditStep.assertRate("13,9%");
        takeCreditStep.editSum("259000");
        takeCreditStep.clickOnTerm();
        takeCreditStep.assertTerm("12 мес");
        takeCreditStep.deleteTermValue();
        takeCreditStep.assertTerm("0 мес");
        takeCreditStep.editTerm("59");
        takeCreditStep.clickCheckbox("Получаю зарплату или пенсию в Сбербанке");
        takeCreditStep.assertTerm("4 года 11 мес");
        takeCreditStep.assertRate("12,9%");
    }
}
