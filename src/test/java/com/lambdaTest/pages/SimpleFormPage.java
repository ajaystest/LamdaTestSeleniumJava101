package com.lambdaTest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SimpleFormPage {
    private WebDriver driver;

    // NOTE: Confirm these locators by inspecting the live page and change if needed.
    private By inputBox = By.id("user-message");           // locator 1: id
    private By showMessageButton = By.cssSelector("button[onclick*='showInput']"); // locator 2: cssSelector
    private By displayedMessage = By.cssSelector("#message"); // locator 3: cssSelector (adjust if needed)

    public SimpleFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterMessage(String text) {
        driver.findElement(inputBox).clear();
        driver.findElement(inputBox).sendKeys(text);
    }

    public void clickShowMessage() {
        driver.findElement(showMessageButton).click();
    }

    public String getDisplayedMessage() {
        WebElement el = driver.findElement(displayedMessage);
        return el.getText().trim();
    }
}