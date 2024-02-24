package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import utilities.AppiumServer;
import utilities.AssertUtils;
import utilities.CommonUtils;

import java.net.MalformedURLException;

public class TestBase {

    public AppiumDriver<MobileElement> driver;
    public static String loadPropertyFile = "Android_timely.properties";
    public static Logger log = Logger.getLogger(TestBase.class);

    public void setUp() {
        if (driver == null) {
            PropertyConfigurator.configure(System.getProperty("user.home")+"/mobile-auto-test/src/test/resources/properties/log4j.properties");

            if (loadPropertyFile.startsWith("Android_")){

                AppiumServer.start();
                log.info("Appium server started");

                CommonUtils.loadAndroidConfProp(loadPropertyFile);
                log.info(loadPropertyFile + " properties file loaded !!!");




                CommonUtils.setAndroidCapabilities();
                try {
                    driver = CommonUtils.getAndroidDriver();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            }else if (loadPropertyFile.startsWith("IOS_")){

            }

        }

    }

    public void quit()  {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
        log.info("Test case execution completed");
        
        AppiumServer.stop();
        log.info("Appium server stopped!");
    }

}
