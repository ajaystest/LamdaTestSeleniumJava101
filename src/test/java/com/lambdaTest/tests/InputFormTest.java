package com.lambdaTest.tests;

import com.lambdaTest.base.BaseTest;
import com.lambdaTest.pages.InputFormPage;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InputFormTest extends BaseTest {

    @Test(dataProvider = "ltCaps", dataProviderClass = BaseTest.class)
    public void inputFormSubmitFlow(MutableCapabilities caps) throws Exception {
        WebDriver driver = createDriver(caps);
        try {
            driver.get("https://www.lambdatest.com/selenium-playground");
            driver.findElement(org.openqa.selenium.By.cssSelector("a[href*='input-form-demo']")).click();

            InputFormPage form = new InputFormPage(driver);

            // 1) Click submit without filling to get error
            form.clickSubmit();
            Thread.sleep(500);
            // Replace the check below with the exact error locator text on the page
            boolean errorShown = driver.getPageSource().contains("Please fill in the fields");
            Assert.assertTrue(errorShown, "Expected validation error not shown");

            // 2) Fill fields and submit
            form.setName("Ajay Selenium");
            form.setEmail("ajay@example.com");
            // fill other fields similarly (add more setters in page object if needed)
            form.selectCountry("United States");
            form.clickSubmit();

            Thread.sleep(1000);

            Assert.assertTrue(form.isSuccessMessageDisplayed(),
                    "Success message not displayed after form submit");

        } finally {
            printSessionId(driver);
            driver.quit();
        }
    }
}