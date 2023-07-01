package main.tests;

import lombok.SneakyThrows;
import main.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckoutTest extends BaseTest {
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
    @Test(dataProvider = "correctCheckOutData")
    public void testSuccessfulConfirmingTheOrder(String firstName, String lastName, String postalCode) {
        catalogPage.addToShoppingCart();
        catalogPage.goToShoppingCart();
        Thread.sleep(1000);
        shoppingCartPage.goToCheckOut();
        checkoutPage.fillCustomerInformation(firstName, lastName, postalCode);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        checkoutPage.submitForm();
        Assert.assertTrue(checkoutOverviewPage.orderInfoIsDisplayed());
    }


    @SneakyThrows
    @BeforeMethod
    public void beforeTestWongData() {
        visit("");
        loginPage = new LoginPage(driver);
        catalogPage = new CatalogPage(driver);
        loginPage.fillLoginFields("standard_user", "secret_sauce");
        shoppingCartPage = new ShoppingCartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
    }

    @Test(dataProvider = "wrongCheckOutData")
    public void testConfirmingTheOrderWithWrongData(String firstName, String lastName, String postalCode) throws InterruptedException {
        catalogPage.addToShoppingCart();
        catalogPage.goToShoppingCart();
        shoppingCartPage.goToCheckOut();
        checkoutPage.fillCustomerInformation(firstName, lastName, postalCode);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        checkoutPage.submitForm();
        Thread.sleep(2000);
        String expectedErrorMessage = checkoutPage.getErrorField();
        Assert.assertEquals(checkoutPage.getErrorField(), expectedErrorMessage);
    }

}
