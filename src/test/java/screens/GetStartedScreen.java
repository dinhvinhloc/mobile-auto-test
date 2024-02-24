package screens;

import base.ScreenBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class GetStartedScreen extends ScreenBase {

    @AndroidFindBy(id = "btn_drawable_tv")
    public WebElement continueButton;

    public GetStartedScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public NextScreenOne clickGetStartedBtn(){
        continueButton.click();
        return new NextScreenOne(driver);
    }
}
