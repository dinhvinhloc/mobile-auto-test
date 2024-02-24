package screens;

import base.ScreenBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddingAddressScreen extends ScreenBase {

    @AndroidFindBy(id = "address_map_edt_unit")
    private WebElement unitTextBox;

    @AndroidFindBy(id = "address_map_edt_street_number")
    private WebElement streetNumberTextBox;

    @AndroidFindBy(id = "address_map_edt_street_name")
    private WebElement streetNameTextBox;

    @AndroidFindBy(id = "address_map_edt_city")
    private WebElement cityTextBox;

    @AndroidFindBy(id = "address_map_edt_postal")
    private WebElement postalCodeTextBox;

    @AndroidFindBy(id = "address_map_edt_province")
    private WebElement provinceTextBox;

    @AndroidFindBy(id = "address_map_edt_country")
    private WebElement countryTextBox;

    @AndroidFindBy(id = "address_map_tv_choose_on_map")
    private WebElement chooseOnMapLink;

    @AndroidFindBy(id = "address_map_btn_search")
    private WebElement searchAddressButton;

    @AndroidFindBy(id = "address_map_btn_save")
    private WebElement saveAddressButton;

    public AddingAddressScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void inputAddress(String unit, String streetNum, String streetName, String city, String postal, String province, String country) {
        if (unit != null){
            unitTextBox.sendKeys(unit);
        }

        if (streetNum != null){
            streetNumberTextBox.sendKeys(streetNum);
        }

        if (streetName != null){
            streetNameTextBox.sendKeys(streetName);
        }

        if (city != null){
            cityTextBox.sendKeys(city);
        }

        if (postal != null){
            postalCodeTextBox.sendKeys(postal);
        }

        if (province != null){
            provinceTextBox.sendKeys(province);
        }

        if (country != null){
            countryTextBox.sendKeys(country);
        }
    }

    public void clickSearchAddress(){
        searchAddressButton.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickSaveAddress(){
        saveAddressButton.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
