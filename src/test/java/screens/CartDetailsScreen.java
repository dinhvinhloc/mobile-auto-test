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
import java.util.concurrent.TimeUnit;

import static utilities.OnScreenActionUtils.scrollDown;

public class CartDetailsScreen extends ScreenBase {

    // Add more item link (go back to menu)
    @AndroidFindBy(id = "shopping_cart_tv_add_more_items")
    public WebElement addMoreItemsLink;

    // Price text
    @AndroidFindBy(id = "shopping_cart_tv_price")
    public WebElement priceText;

    // Checkout button
    @AndroidFindBy(id = "shopping_cart_btn_checkout")
    public WebElement checkoutButton;

    // Coupon text input
    @AndroidFindBy(id = "shopping_cart_edt_coupon")
    public WebElement couponText;

    // Apply coupon button
    @AndroidFindBy(id = "shopping_cart_btn_coupon")
    public WebElement applyCouponButton;

    // Sub-total text
    @AndroidFindBy(id = "shopping_cart_tv_sub_total")
    public WebElement subtotalText;

    // Perk amount text
    @AndroidFindBy(id = "shopping_cart_tv_perks")
    public WebElement perkAmountText;

    // HST amount text
    @AndroidFindBy(id = "shopping_cart_tv_hst")
    public WebElement hstAmountText;

    // HST amount text
    @AndroidFindBy(id = "shopping_cart_tv_total")
    public WebElement totalAmountText;


    public CartDetailsScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }

    public void removeAllItems() {
        List<MobileElement> items;
        items = driver.findElementsById("layout_cart_item_imv");

        while (items.size() > 0) {
                items.get(0).click();
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                items = driver.findElementsById("layout_cart_item_imv");
        }
    }

    public PerkSuggestionsScreen clickPerkSuggestions(){
        List<MobileElement> shoppingCartPromotions = driver.findElements(By.id("layout_cart_tv_name"));

        for(int i=0;i<shoppingCartPromotions.size();i++){

            if(shoppingCartPromotions.get(i).getAttribute("text").contains("perk suggestions")){
                shoppingCartPromotions.get(i).click();
            }
            break;
        }
        return new PerkSuggestionsScreen(driver);
    }


    public void clickOnPromotion(String promotion){
        List<MobileElement> shoppingCartPromotions ;
        String previousLastPromotion = "";
        boolean found = false;
        while (!found){
            shoppingCartPromotions = driver.findElements(By.id("layout_cart_tv_name"));
            if (shoppingCartPromotions.size() != 0) {
                previousLastPromotion = shoppingCartPromotions.get(shoppingCartPromotions.size() - 1).getAttribute("text");
                System.out.println("xxxxxxxxxxxxxxxxxxxxxx"+previousLastPromotion);
                if (shoppingCartPromotions.get(shoppingCartPromotions.size() - 1).getAttribute("text") == previousLastPromotion) {
                    break;
                } else {
                    for (int i = 0; i < shoppingCartPromotions.size(); i++) {
                        if (shoppingCartPromotions.get(i).getAttribute("text").equals(promotion)) {
                            shoppingCartPromotions.get(i).click();
                            found = true;
                            break;
                        }
                    }
                }
            }
            scrollDown(driver);
        }
    }


    public StoreMenuScreen clickAddMoreItems(){
//        addMoreItemsLink.click();
//        This is the list of products added to the cart that's being shown on the screen
        List<MobileElement> productsList;
        String previousLastProduct = "";
//          Getting the list of Add More Items (There is only one but getting a list won't throw exception)
        List<MobileElement> addMoreItemsLinks;

//        Get all the name of the products in the cart
        productsList = driver.findElementsById("layout_cart_item_tv_name");

        boolean found = false;

        while (!found) {
            productsList = driver.findElementsById("layout_cart_item_tv_name");
            addMoreItemsLinks = driver.findElementsById("shopping_cart_tv_add_more_items");
            if (addMoreItemsLinks.size()==0) {

                if (productsList.get(productsList.size()-1).getAttribute("text") == previousLastProduct){
                    break;
                }

                previousLastProduct = productsList.get(productsList.size()-1).getAttribute("text");
                scrollDown(driver);
            } else {
                addMoreItemsLinks.get(0).click();
                found = true;
            }
        }


        return new StoreMenuScreen(driver);
    }

    public void clickGlobalPointsSelection(){
        List<MobileElement> shoppingCartPromotions = driver.findElements(By.id("layout_cart_tv_name"));

        for(int i=0;i<shoppingCartPromotions.size();i++){

            if(shoppingCartPromotions.get(i).getAttribute("text").contains("Global points")){
                shoppingCartPromotions.get(i).click();
                break;
            }
        }
    }

    public void applyCoupon(String coupon){
        couponText.sendKeys(coupon);
        applyCouponButton.click();
    }

    public ServeOptionsScreen clickCheckout(){
        checkoutButton.click();
        return new ServeOptionsScreen(driver);
    }

    public void assertAmountOf(String amountType, String amount){
        switch (amountType) {
            case "sub_total_amount":
                Assert.assertEquals(subtotalText.getText(),amount,"Sub-total assertion");
                break;
            case "total_perks_amount":
                Assert.assertEquals(perkAmountText.getText(),amount,"Total perks amount assertion");
                break;
            case "hst_amount":
                Assert.assertEquals(hstAmountText.getText(),amount,"HST amount assertion");
                break;
            case "total_amount":
                Assert.assertEquals(totalAmountText.getText(),amount,"Total amount assertion");
                break;
            case "total_price":
                Assert.assertEquals(priceText.getText(),amount,"Total price amount assertion");
                break;

        }
    }

}
