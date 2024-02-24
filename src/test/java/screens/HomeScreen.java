package screens;

import base.ScreenBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomeScreen extends ScreenBase {

    @AndroidFindBy(id = "permission_allow_button")
    public WebElement allowButton;


    // Store search text box locators
    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.CHAIN)
    @AndroidFindBy(id = "home_search_view")
    @AndroidFindBy(id = "search_view_edt_search")
    public WebElement searchTextBox;

    // Clear Store search text box button locators
    @AndroidFindBy(id = "search_view_btn_close")
    public WebElement clearSearchButton;

    // Store locator on Home screen after search
    @AndroidFindBy(id = "item_popular_place_imv")
    public WebElement myStore;

    public HomeScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }

    public void InputSearchText(String storeName) {
        searchTextBox.sendKeys(storeName);
    }

    public void clickAllowButton() {
        allowButton.click();
    }

    public void clickClearSearchButton() {
        clearSearchButton.click();
    }

    public void clickOnMyStore(){
        myStore.click();
    }


}
