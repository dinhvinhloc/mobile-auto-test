package screens;

import base.ScreenBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class NextScreenOne extends ScreenBase {

    @AndroidFindBy(accessibility = "Skip")
    public WebElement skipButton;

    public NextScreenOne(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }

    public LoginScreen clickSkipButton(){
        skipButton.click();
        return new LoginScreen(driver);
    }
}
