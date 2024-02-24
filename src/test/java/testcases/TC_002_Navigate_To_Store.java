package testcases;

import base.TestBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import screens.GetStartedScreen;
import screens.HomeScreen;
import screens.LoginScreen;
import screens.NextScreenOne;

public class TC_002_Navigate_To_Store extends TestBase {

    GetStartedScreen startScreen;
    NextScreenOne nextScreen;
    LoginScreen loginScreen;
    HomeScreen homeScreen;


    @BeforeTest
    public void init() {
        setUp();

        startScreen = new GetStartedScreen(driver);
        nextScreen = new NextScreenOne(driver);
        loginScreen = new LoginScreen(driver);
        homeScreen = new HomeScreen(driver);
    }

    @Test (priority = 1)
    public void userLogin() {

        startScreen.clickGetStartedBtn();
        nextScreen.clickSkipButton();

        loginScreen.InputEmail("timelyuser0001@gmail.com");
        loginScreen.InputPassword("Aw3se4dr5ft^");
        loginScreen.clickLoginButton();
    }

    @Test (priority = 2)
    public void searchAndSelectFirstStore() {
        homeScreen.InputSearchText("Merchant timelymerchant19@gmail.com Store");
        homeScreen.clickOnMyStore();
    }


    @AfterTest
    public void quitDriver() {
        quit();

    }

}
