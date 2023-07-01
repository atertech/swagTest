package main.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutOverviewPage extends BasePage {
    @FindBy(xpath = "//*[@id=\"checkout_summary_container\"]/div/div[2]")
    WebElement orderInfo;
    @FindBy(xpath = "//*[@id=\"cancel\"]")
    WebElement cancelOrder;
    @FindBy(xpath = "//*[@id=\"finish\"]")
    WebElement finishButton;

    public CheckoutOverviewPage(WebDriver driver) {

    }

    public boolean orderInfoIsDisplayed() {
        return orderInfo.isDisplayed();
    }
    public void confirmOrder(){
        finishButton.click();
    }
    public void cancelOrder(){
        cancelOrder.click();
    }
}
