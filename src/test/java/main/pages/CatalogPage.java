package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;



public class CatalogPage extends BasePage {
    @FindBy(xpath = "//*[@id=\"header_container\"]/div[2]/div/span/select")
    WebElement filter;
    @FindBy(xpath = "//*[@id=\"inventory_container\"]")
    public
    WebElement productCatalog;
    @FindBy(xpath = "//button[@class='btn btn_primary btn_small btn_inventory']")
    WebElement addToShoppingCartButton;
    @FindBy(xpath = "//button[@class='btn btn_secondary btn_small btn_inventory']")
    WebElement removeFromShoppingCart;
    @FindBy(xpath = "//*[@id=\"shopping_cart_container\"]/a/span")
    public
    WebElement productCounter;
    @FindBy(xpath = "//*[@id=\"shopping_cart_container\"]/a")
    WebElement shoppingCart;

    public List<Double> getProductsPrices() {
        List<Double> productPrices = new ArrayList<>();
        List<WebElement> productCarts = driver.findElements(By.cssSelector("#inventory_container > div > div:nth-child(4) > div.inventory_item_description > div.pricebar > div"));
        for (WebElement productCart : productCarts) {
            String productPrice = productCart.getText().replace("$", "");
            double price = Double.parseDouble(productPrice);
            productPrices.add(price);
        }
        return productPrices;
    }

    public List<String> getProductNames() {
        List<String> productNames = new ArrayList<>();
        List<WebElement> productCarts = driver.findElements(By.className("inventory_item_name"));
        for (WebElement productCart : productCarts) {
            String productName = productCart.getText();
            productNames.add(productName);
        }
        return productNames;
    }


    public CatalogPage(WebDriver driver) {
    }

    public void catalogDivIsPresent() {

        assertThat(productCatalog.isDisplayed()).isTrue();
    }

    public void addToShoppingCart() {
        addToShoppingCartButton.click();
    }

    public void removeFromShoppingCart() {
        removeFromShoppingCart.click();
    }

    public int getCartCount() {
        return Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).
                getText());
    }

    public void filterFromLowToHighPrice() {
        Select priceFromLowToHigh = new Select(filter);
        priceFromLowToHigh.selectByValue("lohi");
    }

    public void filterFromHighToLowPrice() {
        Select priceFromLowToHigh = new Select(filter);
        priceFromLowToHigh.selectByValue("hilo");
    }

    public void filterByNameFromAtoZ() {
        Select nameFromAtoZ = new Select(filter);
        nameFromAtoZ.selectByValue("az");
    }

    public void filterByNameFromZtoA() {
        Select nameFromZtoA = new Select(filter);
        nameFromZtoA.selectByValue("za");
    }

    public void goToShoppingCart() {
        shoppingCart.click();
    }
    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

}
