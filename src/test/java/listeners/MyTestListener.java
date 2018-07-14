package listeners;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import tests.Abstract.AbstractTest;
import utilities.Screenshot;

/**
 * Created by thuan on 17/01/2017.
 */
public class MyTestListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult tr) {
        Screenshot.takeScreenShotOnFailure(((AbstractTest) tr.getInstance()).getDriver(),
                tr.getMethod().getMethodName(), tr.getTestClass().getRealClass());
    }
}
