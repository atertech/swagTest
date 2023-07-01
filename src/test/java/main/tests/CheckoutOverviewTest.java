package main.tests;

import lombok.SneakyThrows;
import main.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutOverviewTest extends BaseTest {
    LoginPage loginPage;
    CatalogPage catalogPage;
    ShoppingCartPage shoppingCartPage;
    CheckoutPage checkoutPage;
    CheckoutOverviewPage checkoutOverviewPage;
    FinishPage finishPage;

    @BeforeMethod
    public void beforeAll() {
        visit("");
        loginPage = new LoginPage(driver);
        catalogPage = new CatalogPage(driver);
        loginPage.fillLoginFields("standard_user", "secret_sauce");
        shoppingCartPage = new ShoppingCartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        catalogPage.addToShoppingCart();
        catalogPage.goToShoppingCart();
        shoppingCartPage.goToCheckOut();
        checkoutPage.fillCustomerInformation("firstName", "lastName", "postalCode");
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        finishPage = new FinishPage();
    }

    @SneakyThrows
    @Test
    public void testFinishOrder() {
        Thread.sleep(1000);
        checkoutPage.submitForm();
        checkoutOverviewPage.confirmOrder();
        Assert.assertTrue(finishPage.completedOrder());
    }

    @Test
    public void testCancelOrder() {
        checkoutPage.submitForm();
        checkoutOverviewPage.cancelOrder();
        assertThat(catalogPage.productCatalog.isDisplayed()).isTrue();
    }

}
