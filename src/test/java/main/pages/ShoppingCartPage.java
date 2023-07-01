package main.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage {
    @FindBy (xpath = "//*[@id=\"checkout\"]")
    WebElement checkoutButton;
    @FindBy (xpath = "/html/body/div/div/div/div[2]/div/div[2]/button[1]")
    WebElement continueShoppingButton;
    @FindBy (xpath = "//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]")
    WebElement cartItem;

    public ShoppingCartPage(WebDriver driver) {
    }

    public void continueShopping(){
        continueShoppingButton.click();
    }
    public boolean verifyThatProductInCart(){
        return cartItem.isDisplayed();
    }

    public void goToCheckOut(){
        checkoutButton.click();
    }

}

