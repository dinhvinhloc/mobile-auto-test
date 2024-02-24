package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.support.ui.WebDriverWait;


//import io.appium.java_client.android.AndroidDriver;

public class ScreenBase {
    public static AppiumDriver<MobileElement> driver;
    public WebDriverWait wait;

    public ScreenBase(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void hideKeyboard() {
        driver.hideKeyboard();
    }

    public void enter() {
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
    }
}
