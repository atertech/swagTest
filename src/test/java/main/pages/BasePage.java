package main.pages;

import main.tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public abstract class BasePage {


    public static WebDriver driver;
    public  BasePage(){
        driver = BaseTest.getDriver();
        PageFactory.initElements(driver, this);
    }

}
