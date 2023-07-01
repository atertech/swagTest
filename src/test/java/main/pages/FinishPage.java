package main.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FinishPage extends BasePage {
    @FindBy(xpath = "//*[@id=\"checkout_complete_container\"]")
    WebElement thankYorForYourOrder;
    @FindBy(xpath = "//*[@id=\"back-to-products\"]")
    WebElement returnToCatalog;

    public boolean completedOrder() {
        return thankYorForYourOrder.isDisplayed();
    }
    public void confirmOrder(){
        returnToCatalog.click();
    }
}
