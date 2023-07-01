package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductPage extends BasePage {
    @FindBy(xpath = "//*[@id=\"inventory_item_container\"]/div/div")
    WebElement productCard;
    @FindBy(xpath = "//button[@class='btn btn_primary btn_small btn_inventory']")
    WebElement addToShoppingCartButton;
    @FindBy(xpath = "//button[@class='btn btn_secondary btn_small btn_inventory']")
    WebElement removeFromShoppingCartButton;
    @FindBy(xpath = "//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[3]")
    WebElement productPrice;
    @FindBy(xpath = "//*[@id=\"item_0_title_link\"]/div")
     WebElement productName;

    public ProductPage(WebDriver driver) {
    }

    public void addProductToShoppingCartFromProductCard(){
        addToShoppingCartButton.click();
    }

    public void removeProductFromShoppingCartFromProductPage(){
        removeFromShoppingCartButton.click();
    }
    public void productCardDivIsPresent(){
        assertThat(productCard.isDisplayed()).isTrue();
    }
    public void goToProductCart(){
        productName.click();
    }
    public void ResetAppState(){
        WebElement menu = driver.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]"));
        menu.click();
        WebElement resetShoppingCart = driver.findElement(By.xpath("//*[@id=\"reset_sidebar_link\"]"));
        resetShoppingCart.click();
        removeFromShoppingCartButton.click();
    }

}
