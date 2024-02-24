package testcases;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import screens.*;

import java.util.concurrent.TimeUnit;

public class TC_006_Checkout_And_Pay_Order extends TestBase {

    GetStartedScreen startScreen;
    NextScreenOne nextScreen;
    LoginScreen loginScreen;
    HomeScreen homeScreen;
    StoreHomeScreen storeHomeScreen;
    StoreMenuScreen storeMenuScreen;
    AddingProductDetailScreen addingProductDetailScreen;
    CartDetailsScreen cartDetailsScreen;
    AddingFreeItemScreen addingFreeItemScreen;
    AddingFreeItemDetailScreen addingFreeItemDetailScreen;
    ServeOptionsScreen serveOptionsScreen;
    OrderConfirmScreen orderConfirmScreen;
    OrderPaymentScreen orderPaymentScreen;
    FinalScreen finalScreen;

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
        addingFreeItemScreen = new AddingFreeItemScreen(driver);
        addingFreeItemDetailScreen = new AddingFreeItemDetailScreen(driver);
        serveOptionsScreen = new ServeOptionsScreen(driver);
        orderConfirmScreen = new OrderConfirmScreen(driver);
        orderPaymentScreen = new OrderPaymentScreen(driver);
        finalScreen = new FinalScreen(driver);
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
    public void clearCart(){
        if (!storeMenuScreen.isCartEmpty()){
            storeMenuScreen.clickOnCart();
            cartDetailsScreen.removeAllItems();
        }
    }

    @Test (priority = 5)
    public void addItemToCart()
    {
        storeMenuScreen.clickOnProduct("PG3P1");
        addingProductDetailScreen.checkAddonWithQty("AG3AO1","3");
        addingProductDetailScreen.clickAddToCart();

        storeMenuScreen.clickOnProduct("PG3P2");
        addingProductDetailScreen.checkAddonWithQty("AG3AO2","3");
        addingProductDetailScreen.clickAddToCart();

        storeMenuScreen.clickOnProduct("PG3P3");
        addingProductDetailScreen.checkAddonWithQty("AG3AO2","3");
        addingProductDetailScreen.increaseProductQtyTo(3);
        addingProductDetailScreen.clickAddToCart();

        storeMenuScreen.clickOnProduct("PG3P4");
        addingProductDetailScreen.checkAddonWithQty("AG3AO1","3");
        addingProductDetailScreen.increaseProductQtyTo(3);
        addingProductDetailScreen.clickAddToCart();
    }

    @Test (priority = 6)
    public void addMoreItemsAndAssertCart(){
        if (!storeMenuScreen.isCartEmpty()){
            storeMenuScreen.clickOnCart();

            cartDetailsScreen.clickOnPromotion("FREE PG3P1 AT $250 BILL");
            addingFreeItemScreen.clickFreeItem();
            addingFreeItemDetailScreen.checkAddonWithQty("AG2AO1","3");
            addingFreeItemDetailScreen.clickAddToMyCart();
            addingFreeItemScreen.assertChosenFreeItem("PG3P1");
            addingFreeItemScreen.assertChosenFreeItemAddon("AG2AO1","3");
            addingFreeItemScreen.clickAddToOrderButton();

            cartDetailsScreen.clickOnPromotion("FREE PG3P1 AT $250 BILL DUP");
            addingFreeItemScreen.clickFreeItem();
            addingFreeItemDetailScreen.checkAddonWithQty("AG2AO2","5");
            addingFreeItemDetailScreen.clickAddToMyCart();
            addingFreeItemScreen.assertChosenFreeItem("PG3P1");
            addingFreeItemScreen.assertChosenFreeItemAddon("AG2AO2","5");
            addingFreeItemScreen.clickAddToOrderButton();

            cartDetailsScreen.applyCoupon("HIDDEN");

            cartDetailsScreen.assertAmountOf("sub_total_amount","$689.96");
            cartDetailsScreen.assertAmountOf("total_perks_amount","-$173.91");
            cartDetailsScreen.assertAmountOf("hst_amount","$67.09");
            cartDetailsScreen.assertAmountOf("total_amount","$583.13");
            cartDetailsScreen.assertAmountOf("total_price","$583.13");

        }
    }

    @Test (priority = 7)
    public void checkout(){

        cartDetailsScreen.clickCheckout();
        serveOptionsScreen.chooseServeOption("dine_in");
        serveOptionsScreen.proceedOrder();

    }

    @Test (priority = 8)
    public void confirm(){

        orderConfirmScreen.assertAmountOf("sub_total_amount","$689.96");
        orderConfirmScreen.assertAmountOf("total_perks_amount","-$173.91");
        orderConfirmScreen.assertAmountOf("hst_amount","$67.09");
        orderConfirmScreen.assertAmountOf("total_amount","$583.13");
        orderConfirmScreen.assertAmountOf("total_price","$583.13");

        orderConfirmScreen.confirmOder();
    }

    @Test (priority = 9)
    public void pay(){
        orderPaymentScreen.selectFirstCard();
        orderPaymentScreen.payOrder();
    }

    @Test (priority = 10)
    public void assertOrderStatus(){
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.presenceOfElementLocated((By) finalScreen.orderStatusText));

        finalScreen.assertOrderStatus("PAID");
    }

    @AfterTest
    public void quitDriver() {
        quit();
    }

}
