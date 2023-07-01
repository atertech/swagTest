package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {
    By firstNameInput = By.xpath("//*[@id=\"first-name\"]");
    By lastNameInput = By.xpath ("//*[@id=\"last-name\"]");
    By postalCodeInput = By.xpath( "//*[@id=\"postal-code\"]");

    By continueToOverviewOrderButton = By.xpath("//*[@id=\"continue\"]");

    @FindBy(xpath = "//*[@id=\"checkout_info_container\"]/div/form/div[1]")
    public
    WebElement checkoutForm;
    @FindBy(xpath = "//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]")
    public
    WebElement errorMessage;

    public CheckoutPage(WebDriver driver) {
    }

    public void fillCustomerInformation(String firstName, String lastName, String postalCode){
        WebElement firstNameField = driver.findElement(firstNameInput);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        WebElement lastNameField = driver.findElement(lastNameInput);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        WebElement postalCodeField = driver.findElement(postalCodeInput);
        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);

    }

    public void submitForm(){
        WebElement continueButton = driver.findElement(continueToOverviewOrderButton);
        continueButton.click();
    }
    public boolean checkoutFormIsDisplayed() {
       return checkoutForm.isDisplayed();
    }


    public String getErrorField() {
        if (errorMessage.isDisplayed()) {
            String errorMessageText = errorMessage.getText();
            String fieldName = errorMessageText.substring(errorMessageText.indexOf(":") + 2);
            return "Error: " + fieldName + " is required";
        }
        return "";
    }
}
