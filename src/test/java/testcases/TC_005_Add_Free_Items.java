package testcases;

import base.TestBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import screens.*;

public class TC_005_Add_Free_Items extends TestBase {

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



            cartDetailsScreen.clickAddMoreItems();
            storeMenuScreen.clickOnProduct("PG3P5");
            addingProductDetailScreen.checkAddonWithQty("AG2AO1","3");
            addingProductDetailScreen.increaseProductQtyTo(3);
            addingProductDetailScreen.clickAddToCart();

            storeMenuScreen.clickOnCart();

            cartDetailsScreen.assertAmountOf("sub_total_amount","$809.11");
            cartDetailsScreen.assertAmountOf("total_perks_amount","-$85.02");
            cartDetailsScreen.assertAmountOf("hst_amount","$94.13");
            cartDetailsScreen.assertAmountOf("total_amount","$818.22");
            cartDetailsScreen.assertAmountOf("total_price","$818.22");


        }
    }

    @Test (priority = 7)
    public void addFreeItems(){
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


        cartDetailsScreen.clickOnPromotion("FREE PG3P2 AT $600 BILL");
        addingFreeItemScreen.clickFreeItem();
        addingFreeItemDetailScreen.checkAddonWithQty("AG3AO1","6");
        addingFreeItemDetailScreen.clickAddToMyCart();
        addingFreeItemScreen.assertChosenFreeItem("PG3P2");
        addingFreeItemScreen.assertChosenFreeItemAddon("AG3AO1","6");
        addingFreeItemScreen.clickAddToOrderButton();

        cartDetailsScreen.assertAmountOf("sub_total_amount","$1,007.35");
        cartDetailsScreen.assertAmountOf("total_perks_amount","-$224.89");
        cartDetailsScreen.assertAmountOf("hst_amount","$101.72");
        cartDetailsScreen.assertAmountOf("total_amount","$884.18");
        cartDetailsScreen.assertAmountOf("total_price","$884.18");

    }

    @AfterTest
    public void quitDriver() {
        quit();
    }

}
