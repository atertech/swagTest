package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPage extends BasePage {
    By loginFieldBy = By.xpath("//*[@id=\"user-name\"]");
    By passwordFieldBy = By.xpath("//*[@id=\"password\"]");
    By loginButtonBy = By.xpath("//*[@id=\"login-button\"]");
    @FindBy(xpath = "//*[@id=\"login_button_container\"]/div/form/div[3]")
    public
    WebElement loginErrorMessage;

    public LoginPage(WebDriver driver) {
    }

    public void fillLoginFields(String login, String password) {
        WebElement loginField = driver.findElement(loginFieldBy);
        loginField.clear();
        loginField.sendKeys(login);

        WebElement passwordField = driver.findElement(passwordFieldBy);
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement button = driver.findElement(loginButtonBy);
        button.click();
    }
    public void loginErrorIsDisplayed(){
        assertThat(loginErrorMessage.isDisplayed()).isTrue();
    }

}
