package screens;

import base.ScreenBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


import java.util.List;
import java.util.NoSuchElementException;

import static utilities.OnScreenActionUtils.scrollDown;

public class StoreMenuScreen extends ScreenBase {
    // Number of items in cart
    @AndroidFindBy(id = "merchant_top_tv_cart")
    public WebElement cartQtyText;

    // Cart icon
    @AndroidFindBy(id = "merchant_top_imv_cart")
    public WebElement cart;

    public StoreMenuScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }

    public MobileElement getProduct(String productName) {

        String id = "item_merchant_menu_product_tv_title";

        MobileElement product = null;

        List<MobileElement> products = driver.findElements(By.id(id));

        System.out.println("Number of products: "+ products.size());

        for(int i=0;i<products.size();i++){

            if(products.get(i).getAttribute("text").equals(productName)){
                product = products.get(i);
            }
        }
        return product;

    }

    public List<MobileElement> getProductsList(){
        return driver.findElementsById("item_merchant_menu_product_tv_title");
    }

    public List<MobileElement> getClosedProductGroup(){
        return driver.findElementsByAccessibilityId("Closed");
    }


    public void clickOnProduct(String productName){
        List<MobileElement> productsList;
        String previousLastProduct = "";
        List<MobileElement> closedProductGroup;
        MobileElement product;

        boolean found = false;

        while (!found) {
            productsList = getProductsList();
            closedProductGroup = getClosedProductGroup();
            product = getProduct(productName);
            if (product==null) {

                System.out.println("Number of Closed product groups: "+ closedProductGroup.size());
                if (closedProductGroup != null){
                    for (int i = closedProductGroup.size()-1;i >= 0; i--){
                        closedProductGroup.get(i).click();
                    }

                }

                if (productsList.get(productsList.size()-1).getAttribute("text") == previousLastProduct){
                    break;
                }

                previousLastProduct = productsList.get(productsList.size()-1).getAttribute("text");
                scrollDown(driver);
            } else {
                product.click();
                found = true;
            }
        }
    }

    public boolean isCartEmpty() {

        if (cartQtyText.getText() == "0"){
            return true;
        } else {
            return false;
        }

    }

    public CartDetailsScreen clickOnCart(){
        cart.click();
        return new CartDetailsScreen(driver);
    }


}
