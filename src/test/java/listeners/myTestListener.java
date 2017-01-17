package listeners;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import pages.AbstractPage;
import utilities.Screenshot;

/**
 * Created by thuan on 17/01/2017.
 */
public class myTestListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult tr) {
        System.out.println("Running onTestFailure");
        Screenshot.takeScreenShot(((AbstractPage)tr.getInstance()).getDriver(), tr.getMethod().getMethodName(), getClass());
    }
}
