package utils;

import io.qameta.allure.junit4.AllureJunit4;
import org.junit.runner.notification.Failure;
import steps.BaseStep;

public class AllureListener extends AllureJunit4 {

    @Override
    public void testFailure(Failure failure) throws Exception {
        BaseStep.takeScreenshot();
        super.testFailure(failure);
    }
}
