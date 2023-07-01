package main.tests;

import lombok.SneakyThrows;
import main.pages.CatalogPage;
import main.pages.LoginPage;
import main.pages.ProductPage;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductPageTest extends BaseTest {
    CatalogPage catalogPage;
    LoginPage loginPage;
    ProductPage productPage;

    @BeforeClass
    public void beforeAll() {
        visit("");
        loginPage = new LoginPage(driver);
        catalogPage = new CatalogPage(driver);
        productPage = new ProductPage(driver);
        loginPage.fillLoginFields("standard_user", "secret_sauce");
        productPage.goToProductCart();
    }

    @Test
    public void testAddProductFromProductPageToShoppingCart() throws InterruptedException {
        Thread.sleep(500);
        productPage.addProductToShoppingCartFromProductCard();
        int actualNumberOfProductsInShoppingCart = catalogPage.getCartCount();
        int expectedNumberOfProducts = 1;
        Assert.assertEquals(expectedNumberOfProducts, actualNumberOfProductsInShoppingCart);
        productPage.ResetAppState();
    }

    @SneakyThrows
    @Test
    public void testRemoveProductFromShoppingListFromProductPage() {
        productPage.addProductToShoppingCartFromProductCard();
        Thread.sleep(500);
        boolean actualResult = catalogPage.productCounter.isDisplayed();
        productPage.removeProductFromShoppingCartFromProductPage();
        boolean expectedResult;
        try {
            expectedResult = !catalogPage.productCounter.isDisplayed();
        } catch (NoSuchElementException e) {
            expectedResult = true;
        }
        Assert.assertEquals(expectedResult, actualResult);
    }
}
