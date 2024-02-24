package testcases;

import base.TestBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import screens.*;

public class TC_003_Browse_Store_Items extends TestBase {

    GetStartedScreen startScreen;
    NextScreenOne nextScreen;
    LoginScreen loginScreen;
    HomeScreen homeScreen;
    StoreHomeScreen storeHomeScreen;

    @BeforeTest
    public void init() {
        setUp();

        startScreen = new GetStartedScreen(driver);
        nextScreen = new NextScreenOne(driver);
        loginScreen = new LoginScreen(driver);
        homeScreen = new HomeScreen(driver);
        storeHomeScreen = new StoreHomeScreen(driver);
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

    @Test (priority = 3)
    public void goToStoreHome() {
        storeHomeScreen.clickStoreHomeButton();
    }

    @Test (priority = 3)
    public void goToPunchCardsScreen() {
        storeHomeScreen.clickPunchCardsButton();
    }

    @Test (priority = 3)
    public void goToRewardsScreen() {
        storeHomeScreen.clickRewardsButton();
    }

    @Test (priority = 3)
    public void goToDealsScreen() {
        storeHomeScreen.clickDealsButton();
    }

    @Test (priority = 3)
    public void goToMenuScreen() {
        storeHomeScreen.clickMenuButton();
    }


    @AfterTest
    public void quitDriver() {
        quit();

    }

}
