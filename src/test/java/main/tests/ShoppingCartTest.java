package main.tests;

import lombok.SneakyThrows;
import main.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class ShoppingCartTest extends BaseTest {
    LoginPage loginPage;
    CatalogPage catalogPage;
    ShoppingCartPage shoppingCartPage;
    CheckoutPage checkoutPage;
    CheckoutOverviewPage checkoutOverviewPage;

    @BeforeClass
    public void beforeAll() {
        visit("");
        loginPage = new LoginPage(driver);
        catalogPage = new CatalogPage(driver);
        loginPage.fillLoginFields("standard_user", "secret_sauce");
        shoppingCartPage = new ShoppingCartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
    }

    @SneakyThrows
    @Test(priority = 1)
    public void testVerifyThatProductWasAddedToCart() {
        catalogPage.addToShoppingCart();
        catalogPage.goToShoppingCart();
        Assert.assertTrue(shoppingCartPage.verifyThatProductInCart());
        Thread.sleep(1000);
    }

    @SneakyThrows
    @Test(priority = 2)
    public void testGoToCheckOut() {
        shoppingCartPage.goToCheckOut();
        Thread.sleep(500);
        Assert.assertTrue(checkoutPage.checkoutFormIsDisplayed());
    }

    @Test
    public void testToReturnToCatalog() {
        catalogPage.addToShoppingCart();
        catalogPage.goToShoppingCart();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        shoppingCartPage.continueShopping();
        catalogPage.catalogDivIsPresent();
        Assert.assertTrue(catalogPage.productCatalog.isDisplayed());
    }

}
