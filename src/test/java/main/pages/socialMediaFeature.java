package main.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class socialMediaFeature extends BasePage {
    @FindBy(xpath = "//*[@id=\"page_wrapper\"]/footer/ul/li[1]/a")
    WebElement twitterIcon;

    public socialMediaFeature(WebDriver driver) {
    }

    public void goToTwitter(){
        twitterIcon.click();
    }
}
