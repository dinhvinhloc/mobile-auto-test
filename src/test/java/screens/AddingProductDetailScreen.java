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

import static utilities.OnScreenActionUtils.scrollDown;

public class AddingProductDetailScreen extends ScreenBase {

    @AndroidFindBy(id = "add_on_selection_btn_cart")
    public WebElement addToCartBtn;

    @AndroidFindBy(id = "add_on_selection_tv_price")
    public WebElement productCost;

    public AddingProductDetailScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }

    public List<MobileElement> getAddonsList(){
        return driver.findElementsById("layout_add_on_product_tv_title");
    }
    public List<MobileElement> getQtyList(){
        return driver.findElementsById("title");
    }

    public MobileElement getAddon(String addonName) {

        String id = "layout_add_on_product_tv_title";

        MobileElement addon = null;

        List<MobileElement> addons = driver.findElements(By.id(id));

        for(int i=0;i<addons.size();i++){

            if(addons.get(i).getAttribute("text").equals(addonName)){
                addon = addons.get(i);
            }
        }
        return addon;
    }


    public MobileElement getQty(String qty) {

        String id = "title";

        MobileElement qtyElement = null;

        List<MobileElement> qtyList = driver.findElements(By.id(id));

        for(int i=0;i<qtyList.size();i++){

            if(qtyList.get(i).getAttribute("text").equals(qty)){
                qtyElement = qtyList.get(i);
            }
        }
        return qtyElement;
    }

    public MobileElement getAddonQty(String addonName) {

        String id = "layout_add_on_product_tv_title";
        String idQty = "layout_add_on_product_tv_quantity";

        MobileElement addonQty = null;

        List<MobileElement> addons = driver.findElements(By.id(id));
        List<MobileElement> addonsQty = driver.findElements(By.id(idQty));

        for(int i=0;i<addons.size();i++){

            if(addons.get(i).getAttribute("text").equals(addonName)){
                addonQty = addonsQty.get(i);
            }
        }
        return addonQty;
    }

    public void checkAddonWithQty (String addonName, String qty){
        List<MobileElement> addonList;
        String previousLastAddon ="";
        MobileElement addon;

        boolean found = false;

        while (!found){
            addonList = getAddonsList();
            addon = getAddon(addonName);

            if (addon == null){
                if (addonList.get(addonList.size()-1).getAttribute("text") == previousLastAddon){
                    break;
                }

                previousLastAddon = addonList.get(addonList.size()-1).getAttribute("text");
                scrollDown(driver);
            } else {
                addon.click();

                getAddonQty(addonName).click();
                clickOnQty(qty);
                found = true;
            }
        }

    }

    public void clickOnQty (String Qty) {
        String id = "title";
        List<MobileElement> qties;

        String previousLastQty ="";
        MobileElement qtyElement;

        boolean found = false;

        while (!found){
            qties = getQtyList();
            qtyElement = getQty(Qty);

            if (qtyElement == null){
                if (qties.get(qties.size()-1).getAttribute("text") == previousLastQty){
                    break;
                }

                previousLastQty = qties.get(qties.size()-1).getAttribute("text");
                scrollDown(driver);
            } else {
                qtyElement.click();
                found = true;
            }
        }

    }


    public void increaseProductQtyTo(int qty){
        Boolean isFoundElement;
        By myElement = By.id("add_on_selection_quantity_btn_add");

        isFoundElement = driver.findElements(myElement).size() > 0;
        while (isFoundElement == false){
            scrollDown(driver);
            isFoundElement = driver.findElements(myElement).size() > 0;
        }

        for(int i=0; i<qty-1; i++){
            driver.findElement(myElement).click();
        }
    }


    public void clickAddToCart(){
        addToCartBtn.click();
    }

}


