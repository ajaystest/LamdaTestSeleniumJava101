package com.lambdaTest.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class InputFormPage {
    private WebDriver driver;

    // Adjust these locators after inspecting the real form DOM
    private By submitButton = By.cssSelector("button[type='submit']");
    private By nameInput = By.name("name");
    private By emailInput = By.name("email");
    private By countrySelect = By.name("country");
    private By successMessage = By.xpath("//*[contains(text(),'Thanks for contacting us, we will get back to you shortly.')]");

    public InputFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    public void setName(String name) {
        driver.findElement(nameInput).clear();
        driver.findElement(nameInput).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
    }

    public void selectCountry(String visibleText) {
        Select sel = new Select(driver.findElement(countrySelect));
        sel.selectByVisibleText(visibleText);
    }

    public boolean isSuccessMessageDisplayed() {
        return !driver.findElements(successMessage).isEmpty();
    }
}

