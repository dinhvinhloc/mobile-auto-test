package screens;

import base.ScreenBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ServeOptionsScreen extends ScreenBase {

    @AndroidFindBy(id = "choose_serving_rb_pick_up")
    public WebElement pickupRadioButton;

    @AndroidFindBy(id = "choose_serving_rb_dine_in")
    public WebElement dineInRadioButton;

    @AndroidFindBy(id = "choose_serving_rb_delivery")
    public WebElement deliveryRadioButton;

    @AndroidFindBy(id = "choose_serving_tv_address")
    public WebElement addressText;

    @AndroidFindBy(id = "choose_serving_btn_address")
    public WebElement addDeliveryAddressButton;

    @AndroidFindBy(id = "choose_serving_tv_phone")
    public WebElement phoneNumber;

    @AndroidFindBy(id = "choose_serving_btn_phone")
    public WebElement addPhoneNumberButton;

    @AndroidFindBy(id = "choose_serving_btn_cancel")
    public WebElement cancelOrderButton;

    @AndroidFindBy(id = "dialog_alert_btn_ok")
    public WebElement cancelOrderConfirmButton;

    @AndroidFindBy(id = "choose_serving_btn_proceed")
    public WebElement proceedOrderButton;

    public ServeOptionsScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void chooseServeOption (String serveOption){
        switch (serveOption) {
            case "pick_up":
                pickupRadioButton.click();
                break;
            case "dine_in":
                dineInRadioButton.click();
                break;
            case "delivery":
                deliveryRadioButton.click();
                break;
        }
    }

    public void cancelOrder(){
        cancelOrderButton.click();
        cancelOrderConfirmButton.click();
    }

    public OrderConfirmScreen proceedOrder(){
        proceedOrderButton.click();
        return new OrderConfirmScreen(driver);
    }


    public SelectDeliveryAddress clickAddAddress()
    {
        addDeliveryAddressButton.click();
        return new SelectDeliveryAddress(driver);
    }

    public void addPhoneNumber(String phoneNumber){
        addPhoneNumberButton.click();

        String phoneNumberText = "dialog_phone_edt_phone";
        MobileElement phoneNumberEditText = driver.findElementById(phoneNumberText);
        phoneNumberEditText.sendKeys(phoneNumber);
        driver.findElementById("dialog_phone_btn_ok");


    }

}
