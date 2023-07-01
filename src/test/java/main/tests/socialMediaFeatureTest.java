package main.tests;

import lombok.SneakyThrows;
import main.pages.CatalogPage;
import main.pages.LoginPage;
import main.pages.socialMediaFeature;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class socialMediaFeatureTest extends BaseTest {
    LoginPage loginPage;
    CatalogPage catalogPage;
    socialMediaFeature socialMedia;


    @BeforeClass
    public void beforeAll() {
        visit("");
        loginPage = new LoginPage(driver);
        loginPage.fillLoginFields("standard_user", "secret_sauce");
        socialMedia = new socialMediaFeature(driver);
    }

    @SneakyThrows
    @Test
    public void goToTwitterTest(){
        Thread.sleep(1000);
        String firstTab = driver.getWindowHandle();
        socialMedia.goToTwitter();
        Thread.sleep(2000);
        for (String tabs : driver.getWindowHandles()){
            if(!tabs.equals(firstTab)){
                driver.switchTo().window(tabs);
                break;
            }
        }
        String actualURL = driver.getCurrentUrl();
        Thread.sleep(1000);
        String expectedURL = "https://twitter.com/i/flow/login?redirect_after_login=%2Fsaucelabs";
        Thread.sleep(1000);
        assertEquals(actualURL, expectedURL);
    }
}
