package screens;

import base.ScreenBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPaymentScreen extends ScreenBase {

    @AndroidFindBy(id = "payment_btn_use_card")
    public WebElement useThisCardButton;

    @AndroidFindBy(id = "payment_btn_cancel")
    public WebElement cancelOrderButton;

    @AndroidFindBy(id = "dialog_alert_btn_ok")
    public WebElement cancelOrderConfirmButton;


    public OrderPaymentScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void cancelOrder() {
        cancelOrderButton.click();
        cancelOrderConfirmButton.click();
    }

    public void selectFirstCard() {
        String cardRadioSelectionID = "item_payment_rb";

        List<MobileElement> cardList = driver.findElements(By.id(cardRadioSelectionID));

        if (cardList.size() > 0){
            cardList.get(0).click();
        }

    }

    public FinalScreen payOrder() {
        useThisCardButton.click();
        return new FinalScreen(driver);
    }

    public void removeAllCards(){
        String cardDeleteButtons = "item_payment_btn_delete";

        List<MobileElement> cardList = driver.findElements(By.id(cardDeleteButtons));

        while (cardList.size() > 0){
            cardList.get(0).click();
            cardList = driver.findElements(By.id(cardDeleteButtons));
        }
    }

    public AddingCardScreen clickAddNewCard() {
        String addNewCardLinkID = "item_payment_btn_add";
        MobileElement addNewCardLink = driver.findElementById(addNewCardLinkID);
        addNewCardLink.click();
        return new AddingCardScreen(driver);
    }


}
