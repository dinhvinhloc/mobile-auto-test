package testcases;

import base.TestBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import screens.*;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TC_008_Checkout_Add_Delivery_Address extends TestBase {

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
    AddingCardScreen addingCardScreen;
    SelectDeliveryAddress selectDeliveryAddress;
    AddingAddressScreen addingAddressScreen;

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
        addingCardScreen = new AddingCardScreen(driver);
        selectDeliveryAddress = new SelectDeliveryAddress(driver);
        addingAddressScreen = new AddingAddressScreen(driver);

        finalScreen = new FinalScreen(driver);
    }

    @Test (priority = 1)
    public void userLogin() {

        startScreen.clickGetStartedBtn();
        nextScreen.clickSkipButton();

        loginScreen.InputEmail("automobileuser0001@gmail.com");
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
        storeMenuScreen.clickOnProduct("PG2P1");
        addingProductDetailScreen.checkAddonWithQty("AG2AO1","3");
        addingProductDetailScreen.clickAddToCart();

        storeMenuScreen.clickOnProduct("PG2P2");
        addingProductDetailScreen.checkAddonWithQty("AG2AO2","3");
        addingProductDetailScreen.clickAddToCart();

        storeMenuScreen.clickOnProduct("PG2P3");
        addingProductDetailScreen.checkAddonWithQty("AG2AO2","3");
        addingProductDetailScreen.increaseProductQtyTo(3);
        addingProductDetailScreen.clickAddToCart();

        storeMenuScreen.clickOnCart();

        cartDetailsScreen.assertAmountOf("sub_total_amount","$241.32");
        cartDetailsScreen.assertAmountOf("total_perks_amount","-$3.49");
        cartDetailsScreen.assertAmountOf("hst_amount","$30.92");
        cartDetailsScreen.assertAmountOf("total_amount","$268.75");
        cartDetailsScreen.assertAmountOf("total_price","$268.75");
        cartDetailsScreen.clickCheckout();

    }

    @Test (priority = 6)
    public void checkoutAndAddDeliveryAddress(){

        serveOptionsScreen.chooseServeOption("delivery");
        serveOptionsScreen.clickAddAddress();

        selectDeliveryAddress.removeAllAddress();
        selectDeliveryAddress.clickAddNewAddress();

        addingAddressScreen.inputAddress("0","114","Tandridge Crescent","Etobicoke","M9W 5N9", "Ontario","Canada");
        addingAddressScreen.clickSearchAddress();
        addingAddressScreen.clickSaveAddress();

        selectDeliveryAddress.selectFirstAddress();
        selectDeliveryAddress.clickUseThisAddress();

        serveOptionsScreen.proceedOrder();


    }

    @Test (priority = 7)
    public void confirm(){
        orderConfirmScreen.assertAmountOf("sub_total_amount","$241.32");
        orderConfirmScreen.assertAmountOf("total_perks_amount","-$3.49");
        orderConfirmScreen.assertAmountOf("delivery_fee","$1.00");
        orderConfirmScreen.assertAmountOf("hst_amount","$31.05");
        orderConfirmScreen.assertAmountOf("total_amount","$269.88");
        orderConfirmScreen.assertAmountOf("total_price","$269.88");

        orderConfirmScreen.confirmOder();
    }



    @Test (priority = 8)
    public void addCardAndPay(){

        Format f = new SimpleDateFormat("MM/yy");

        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.YEAR, 1); // to get previous year add -1
        Date nextYear = cal.getTime();

        String strDate = f.format(nextYear);

        orderPaymentScreen.removeAllCards();

        orderPaymentScreen.clickAddNewCard();

//      Add a VISA Debit card, expire next year

        addingCardScreen.enterCardInfo("4000056655665556",strDate,"321","M9W 5N9");
        addingCardScreen.saveCard();


        orderPaymentScreen.selectFirstCard();
        orderPaymentScreen.payOrder();
    }

    @Test (priority = 9)
    public void assertOrderStatus(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

//        WebDriverWait wait = new WebDriverWait(driver, 10);

        finalScreen.assertOrderStatus("PAID");
    }

    @AfterTest
    public void quitDriver() {
        quit();
    }

}
