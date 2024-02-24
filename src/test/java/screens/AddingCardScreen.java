package screens;

import base.ScreenBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddingCardScreen extends ScreenBase {

    @AndroidFindBy(id = "action_save")
    public WebElement saveButton;

    @AndroidFindBy(id = "et_card_number")
    public WebElement cardNumberTextField;

    @AndroidFindBy(id = "et_expiry")
    public WebElement expiryTextField;

    @AndroidFindBy(id = "et_cvc")
    public WebElement cvcTextField;

    @AndroidFindBy(id = "et_postal_code")
    public WebElement postalTextField;

    public AddingCardScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void enterCardInfo(String cardNumber, String expiry, String cvc, String postal){
        cardNumberTextField.sendKeys(cardNumber);
        expiryTextField.sendKeys(expiry);
        cvcTextField.sendKeys(cvc);
        postalTextField.sendKeys(postal);
    }


    public OrderPaymentScreen saveCard(){
        saveButton.click();
        return new OrderPaymentScreen(driver);
    }



}
