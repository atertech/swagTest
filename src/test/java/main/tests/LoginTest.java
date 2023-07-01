package main.tests;

import main.pages.CatalogPage;
import main.pages.LoginPage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BaseTest {

    LoginPage loginPage = new LoginPage(driver);
    CatalogPage catalogPage = new CatalogPage(driver);

    @Test(dataProvider = "loginDataCorrect", priority = 1)
    public void testLogin(String username, String password) {
        loginPage = new LoginPage(driver);
        catalogPage = new CatalogPage(driver);

        visit("");
        loginPage.fillLoginFields(username, password);
        catalogPage.catalogDivIsPresent();

        assertThat(catalogPage.productCatalog.isDisplayed()).isTrue();
    }

    @Test(dataProvider = "loginDataFailed")
    public void unsuccessfulTestLogin(String username, String password) {
        loginPage = new LoginPage(driver);
        visit("");
        loginPage.fillLoginFields(username, password);
        loginPage.loginErrorIsDisplayed();
        assertThat(loginPage.loginErrorMessage.isDisplayed()).isTrue();

    }


}

