package utilities;

/**
 * Created by thuan on 10/01/2017.
 */
public class Sleeper {

    public static void sleep(int timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
