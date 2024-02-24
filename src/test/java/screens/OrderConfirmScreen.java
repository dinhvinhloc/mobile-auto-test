package screens;

import base.ScreenBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class OrderConfirmScreen extends ScreenBase {

    // Price text
    @AndroidFindBy(id = "confirm_order_tv_price")
    public WebElement priceText;

    // Checkout button
    @AndroidFindBy(id = "confirm_order_btn_checkout")
    public WebElement confirmOrderButton;

    // Sub-total text
    @AndroidFindBy(id = "confirm_order_tv_sub_total")
    public WebElement subtotalText;

    // Perk amount text
    @AndroidFindBy(id = "confirm_order_tv_perks")
    public WebElement perkAmountText;

    // HST amount text
    @AndroidFindBy(id = "confirm_order_tv_hst")
    public WebElement hstAmountText;

    // Order total
    @AndroidFindBy(id = "confirm_order_tv_total")
    public WebElement totalAmountText;

    // Order Delivery
    @AndroidFindBy(id = "confirm_order_tv_delivery")
    public WebElement deliveryAmountText;

    public OrderConfirmScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    public OrderPaymentScreen confirmOder(){
        confirmOrderButton.click();
        return new OrderPaymentScreen(driver);
    }

    public void assertAmountOf(String amountType, String amount){
        switch (amountType) {
            case "sub_total_amount":
                Assert.assertEquals(subtotalText.getText(),amount,"Sub-total assertion");
                break;
            case "total_perks_amount":
                Assert.assertEquals(perkAmountText.getText(),amount,"Total perks amount assertion");
                break;
            case "hst_amount":
                Assert.assertEquals(hstAmountText.getText(),amount,"HST amount assertion");
                break;
            case "total_amount":
                Assert.assertEquals(totalAmountText.getText(),amount,"Total amount assertion");
                break;
            case "total_price":
                Assert.assertEquals(priceText.getText(),amount,"Total price amount assertion");
                break;
            case "delivery_fee":
                Assert.assertEquals(deliveryAmountText.getText(),amount,"Delivery fee assertion");
                break;
        }
    }

}
