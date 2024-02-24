package utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CommonUtils {

    private static AppiumDriver<MobileElement> driver;
    private static URL serverUrl;
    private static DesiredCapabilities capabilities = new DesiredCapabilities();
    private static String APPIUM_PORT;
    private static int IMPLICIT_WAIT_TIME;
    private static int EXPLICIT_WAIT_TIME;
    private static String BASE_PKG;
    private static String APP_ACTIVITY;
    private static String APP_PATH;
    private static String BROWSER_NAME;
    private static String PLATFORM_NAME;
    private static String PLATFORM_VERSION;
    private static String DEVICE_NAME;
    private static String UDID;
    private static String BUNDLE_ID;
    private static String APP;
    private static String VALID_EMAIL;
    private static String VALID_PASSWORD;
    private static String AUTO_GRANT_PERMISSIONS;
    private static String AUTO_ACCEPT_ALERTS;





    private static Properties prop = new Properties();

    private static  FileInputStream fis;


    public static void loadAndroidConfProp(String loadPropertyFile) {

        try {
            Path currentPath = Paths.get("");
//            fis = new FileInputStream(System.getProperty("user.home") + "/mobile-auto-test/src/test/resources/properties/" + loadPropertyFile);
            fis = new FileInputStream(currentPath.toAbsolutePath().toString() + "/src/test/resources/properties/" + loadPropertyFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        IMPLICIT_WAIT_TIME = Integer.parseInt(prop.getProperty("implicit.wait"));
        EXPLICIT_WAIT_TIME = Integer.parseInt(prop.getProperty("explicit.wait"));
        BASE_PKG = prop.getProperty("base.pkg");
        APP_ACTIVITY = prop.getProperty("application.activity");
        APP_PATH = prop.getProperty("application.path");
        BROWSER_NAME = prop.getProperty("browser.name");
        PLATFORM_NAME = prop.getProperty("platform.name");
        PLATFORM_VERSION = prop.getProperty("platform.version");
        UDID = prop.getProperty("udid");
        APPIUM_PORT = prop.getProperty("appium.server.port");
        DEVICE_NAME = prop.getProperty("device.name");
        BUNDLE_ID = prop.getProperty("base.pkg");
        APP = prop.getProperty("app");
        VALID_EMAIL = prop.getProperty("valid.email");
        VALID_PASSWORD = prop.getProperty("valid.password");
        AUTO_GRANT_PERMISSIONS = prop.getProperty("appium.autoGrantPermissions");
        AUTO_ACCEPT_ALERTS = prop.getProperty("appium.autoAcceptAlerts");
    }

    // Setting Android Capability
    public static void setAndroidCapabilities(){
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,PLATFORM_NAME);
//        capabilities.setCapability(CapabilityType.BROWSER_NAME,BROWSER_NAME);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,PLATFORM_VERSION);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,DEVICE_NAME);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,BASE_PKG);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,APP_ACTIVITY);
        capabilities.setCapability(MobileCapabilityType.UDID,UDID);
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS ,AUTO_GRANT_PERMISSIONS);
    }

    public static void loadIOSConfProp(String loadPropertyFile) {
    }

    public static void setIOSCapabilities(){

    }

    public static AppiumDriver<MobileElement> getAndroidDriver() throws MalformedURLException {
        serverUrl = new URL("http://localhost:"+APPIUM_PORT+"/wd/hub");
        driver = new AndroidDriver<MobileElement>(serverUrl,capabilities);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);

        return driver;

    }

    public static AppiumDriver<MobileElement> getIOSDriver(){
        return driver;

    }

}
