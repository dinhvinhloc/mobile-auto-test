package utilities;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;

public class AppiumServer {
    public static AppiumDriverLocalService service;

    public static void start() {
        service = AppiumDriverLocalService.buildService(
                new AppiumServiceBuilder().usingDriverExecutable(new File("/usr/local/bin/node"))
                .withAppiumJS(new File("/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib/main.js"))
                .withLogFile(new File(System.getProperty("user.home")+"/mobile-auto-test/src/test/resources/logs/Appium.log"))
                .withArgument(GeneralServerFlag.LOCAL_TIMEZONE));
        service.start();
    }

    public static void stop(){
        service.stop();
    }

}
