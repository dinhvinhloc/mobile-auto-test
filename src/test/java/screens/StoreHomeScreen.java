package screens;

import base.ScreenBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class StoreHomeScreen extends ScreenBase {

    // Home button on the store view
    @AndroidFindBy(accessibility = "Home")
    public WebElement homeButton;

    // Punch Card button on the store view
    @AndroidFindBy(accessibility = "Punch Cards")
    public WebElement punchCardsButton;

    // Rewards button on the store view
    @AndroidFindBy(accessibility = "Rewards")
    public WebElement rewardsButton;

    // Deals button on the store view
    @AndroidFindBy(accessibility = "Deals")
    public WebElement dealsButton;

    // Menu button on the store view
    @AndroidFindBy(accessibility = "Menu")
    public WebElement menuButton;

    public StoreHomeScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }

    public void clickStoreHomeButton() {
        homeButton.click();
    }

    public void clickPunchCardsButton() {
        punchCardsButton.click();
    }

    public void clickRewardsButton() {
        rewardsButton.click();
    }

    public void clickDealsButton() {
        dealsButton.click();
    }

    public void clickMenuButton() {
        menuButton.click();
    }



}
