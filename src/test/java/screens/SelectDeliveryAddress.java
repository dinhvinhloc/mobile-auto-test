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

public class SelectDeliveryAddress extends ScreenBase {

    @AndroidFindBy(id = "delivery_address_btn_use")
    private WebElement useThisAddressButton;

    public SelectDeliveryAddress(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    public void removeAllAddress(){
        String addressDeleteButtons = "item_address_btn_delete";

        List<MobileElement> cardList = driver.findElements(By.id(addressDeleteButtons));

        while (cardList.size() > 0){
            cardList.get(0).click();
            cardList = driver.findElements(By.id(addressDeleteButtons));
        }
    }


    public AddingAddressScreen clickAddNewAddress() {
        String addNewCardLinkID = "item_address_btn_add";
        MobileElement addNewCardLink = driver.findElementById(addNewCardLinkID);
        addNewCardLink.click();
        return new AddingAddressScreen(driver);
    }

    public void selectFirstAddress(){
        String addressRadioSelectionID = "item_address_rb";

        List<MobileElement> cardList = driver.findElements(By.id(addressRadioSelectionID));

        if (cardList.size() > 0){
            cardList.get(0).click();
        }
    }

    public void clickUseThisAddress(){
        useThisAddressButton.click();
    }

}
