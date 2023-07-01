package main.tests;

import lombok.SneakyThrows;
import main.pages.CatalogPage;
import main.pages.LoginPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;


public class CatalogTest extends BaseTest {
    CatalogPage catalogPage;
    LoginPage loginPage;

    @BeforeClass
    public void beforeAll() {
        visit("");
        loginPage = new LoginPage(driver);
        loginPage.fillLoginFields("standard_user", "secret_sauce");
        catalogPage = new CatalogPage(driver);
    }


    @Test(priority = 1)
    public void testFilteringFromLowToHigh() {

        List<Double> beforeFilteringByLoHi = catalogPage.getProductsPrices();
        catalogPage.filterFromLowToHighPrice();
        List<Double> afterFilteringByLoHi = catalogPage.getProductsPrices();
        Collections.sort(beforeFilteringByLoHi);
        Assert.assertEquals(afterFilteringByLoHi, beforeFilteringByLoHi);
    }

    @Test
    public void testFilteringFromHighToLow() {
        List<Double> beforeFiltering = catalogPage.getProductsPrices();
        catalogPage.filterFromHighToLowPrice();
        List<Double> afterFiltering = catalogPage.getProductsPrices();
        Collections.sort(beforeFiltering, Collections.reverseOrder());
        Assert.assertEquals(afterFiltering, beforeFiltering);
    }

    @Test
    public void testFilteringByNameFromAtoZ() {
        List<String> initialListOfProducts = catalogPage.getProductNames();
        catalogPage.filterByNameFromAtoZ();
        List<String> afterFilteringByNameFromAtoZ = catalogPage.getProductNames();
        Collections.sort(initialListOfProducts);
        Assert.assertEquals(afterFilteringByNameFromAtoZ, initialListOfProducts);
    }

    @Test
    public void testFilteringByNameFromZtoA() {
        List<String> initialListOfProducts = catalogPage.getProductNames();
        catalogPage.filterByNameFromZtoA();
        List<String> afterFilteringByNameFromZtoA = catalogPage.getProductNames();
        Collections.sort(initialListOfProducts, Collections.reverseOrder());
        Assert.assertEquals(afterFilteringByNameFromZtoA, initialListOfProducts);
    }

    @Test
    public void testAddToCart() {
        catalogPage.addToShoppingCart();
        int actualCount = catalogPage.getCartCount();
        int expectedCount = 1;
        Assert.assertEquals(expectedCount, actualCount);
    }

    @SneakyThrows
    @Test
    public void testRemove() throws InterruptedException {
        catalogPage.addToShoppingCart();
        int initialNumberOfProducts = catalogPage.getCartCount();
        //Thread.sleep(1500);
        boolean actualResult = catalogPage.isElementPresent(By.xpath("//*[@id='shopping_cart_container']/a/span"));
        catalogPage.removeFromShoppingCart();
        //Thread.sleep(1500);
        if (!catalogPage.isElementPresent(By.xpath("//*[@id='shopping_cart_container']/a/span"))) {
            boolean expectedResult;
            expectedResult = true;
            Assert.assertEquals(expectedResult, actualResult);
        } else {
            int finalNumberOfProducts = catalogPage.getCartCount();
            int finalCount = initialNumberOfProducts - 1;
            Assert.assertEquals(finalCount, finalNumberOfProducts);
        }
    }


}
