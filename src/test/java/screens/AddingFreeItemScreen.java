package screens;

import base.ScreenBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

import static utilities.OnScreenActionUtils.scrollDown;

public class AddingFreeItemScreen extends ScreenBase {

    @AndroidFindBy(id = "layout_choose_reward_tv_title")
    public WebElement freeItem;

    @AndroidFindBy(id = "choose_reward_btn_add")
    public WebElement addToOrderButton;

    @AndroidFindBy(id = "layout_choose_reward_tv_title")
    public WebElement chosenRewardTitle;

    @AndroidFindBy(id = "layout_choose_reward_tv_des")
    public WebElement chosenRewardDescription;

    public AddingFreeItemScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }

    public void clickFreeItem(){
        freeItem.click();
    }

    public void clickAddToOrderButton(){
        addToOrderButton.click();
    }


    public void assertChosenFreeItem(String itemName){
        Assert.assertEquals(chosenRewardTitle.getText(),itemName);
    }

    public void assertChosenFreeItemAddon(String addonName, String qty) {
        if (qty == "1") {
            Assert.assertEquals(chosenRewardDescription.getText(), " ► "+ addonName);
        } else {
            Assert.assertEquals(chosenRewardDescription.getText(), " ► " + qty + " x " + addonName);
        }

    }
}


