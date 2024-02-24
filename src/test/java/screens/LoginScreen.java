package screens;

import base.ScreenBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginScreen extends ScreenBase {

    //    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.CHAIN)
//    @AndroidFindBy(id = "login_edt_email")
//    @AndroidFindBy(id = "edt_drawable_edt")
    @AndroidFindBys(value = {
            @AndroidBy(id = "login_edt_email"),
            @AndroidBy(id = "edt_drawable_edt")
    })
    public WebElement emailTextBox;

//    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.CHAIN)
//    @AndroidFindBy(id = "login_edt_password")
//    @AndroidFindBy(id = "edt_drawable_edt")

    @AndroidFindBys(value = {
            @AndroidBy(id = "login_edt_password"),
            @AndroidBy(id = "edt_drawable_edt")
    })
    public WebElement passwordTextBox;

    @AndroidFindBy(id = "login_btn_login")
    public WebElement loginButton;

    public LoginScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }

    public void InputEmail(String email) {
        emailTextBox.sendKeys(email);
    }

    public void InputPassword(String password) {
        passwordTextBox.sendKeys(password);
    }

    public HomeScreen clickLoginButton() {

        loginButton.click();
        return new HomeScreen(driver);
    }
}
