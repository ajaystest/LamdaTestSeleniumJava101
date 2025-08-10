package com.lambdaTest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SliderPage {
    private WebDriver driver;

    // NOTE: these locators are guesses. Inspect the Drag & Drop Sliders page and correct them if needed.
    private By sliderInput = By.xpath("//input[@type='range' and @value='15']"); // example
    private By sliderValue = By.id("range"); // adjust to actual element that shows value

    public SliderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setSliderValueTo95() {
        WebElement slider = driver.findElement(sliderInput);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = 95; arguments[0].dispatchEvent(new Event('change'))", slider);
    }

    public String getSliderValue() {
        // adjust locator or retrieval logic as per actual page DOM
        return driver.findElement(sliderValue).getText().trim();
    }
}

