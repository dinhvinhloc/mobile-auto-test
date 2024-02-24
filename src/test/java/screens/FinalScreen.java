package screens;

import base.ScreenBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class FinalScreen extends ScreenBase {

    // Order ID Text View
    @AndroidFindBy(id = "confirm_order_tv_number")
    private WebElement orderIDText;

    // Store Name Text View
    @AndroidFindBy(id = "confirm_order_tv_merchant")
    private WebElement storeNameText;

    // Order status text view
    @AndroidFindBy(id = "confirm_order_tv_status")
    public WebElement orderStatusText;

    // Rate Us button
    @AndroidFindBy(id = "confirm_order_btn_rate")
    private WebElement rateUsButton;



    public FinalScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void assertOrderStatus(String status){
        Assert.assertEquals(orderStatusText.getText(),status,"Order Status assertion");

    }

}
