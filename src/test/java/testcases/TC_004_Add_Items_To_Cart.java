package testcases;

import base.TestBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import screens.*;

public class TC_004_Add_Items_To_Cart extends TestBase {

    GetStartedScreen startScreen;
    NextScreenOne nextScreen;
    LoginScreen loginScreen;
    HomeScreen homeScreen;
    StoreHomeScreen storeHomeScreen;
    StoreMenuScreen storeMenuScreen;
    AddingProductDetailScreen addingProductDetailScreen;
    CartDetailsScreen cartDetailsScreen;

    @BeforeTest
    public void init() {
        setUp();

        startScreen = new GetStartedScreen(driver);
        nextScreen = new NextScreenOne(driver);
        loginScreen = new LoginScreen(driver);
        homeScreen = new HomeScreen(driver);
        storeHomeScreen = new StoreHomeScreen(driver);
        storeMenuScreen = new StoreMenuScreen(driver);
        addingProductDetailScreen = new AddingProductDetailScreen(driver);
        cartDetailsScreen = new CartDetailsScreen(driver);
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
    public void goToMenuScreen() {
        storeHomeScreen.clickMenuButton();
    }

    @Test (priority = 4)
    public void addItemToCart()
    {
        storeMenuScreen.clickOnProduct("PG3P1");
        addingProductDetailScreen.checkAddonWithQty("AG3AO1","3");
        addingProductDetailScreen.clickAddToCart();
    }

    @Test (priority = 5)
    public void clearCart(){
        if (!storeMenuScreen.isCartEmpty()){
            storeMenuScreen.clickOnCart();
            cartDetailsScreen.removeAllItems();
        }
    }

    @AfterTest
    public void quitDriver() {
        quit();
    }

}
