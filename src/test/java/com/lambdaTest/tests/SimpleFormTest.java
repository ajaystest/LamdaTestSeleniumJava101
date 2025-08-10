package com.lambdaTest.tests;

import com.lambdaTest.base.BaseTest;
import com.lambdaTest.pages.SimpleFormPage;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleFormTest extends BaseTest {

    @Test(dataProvider = "ltCaps", dataProviderClass = BaseTest.class)
    public void simpleFormDemo(MutableCapabilities caps) throws Exception {
        WebDriver driver = createDriver(caps);
        try {
            driver.get("https://www.lambdatest.com/selenium-playground");
            // click Simple Form Demo link (use CSS by href)
            driver.findElement(org.openqa.selenium.By.cssSelector("a[href*='simple-form-demo']")).click();

            // validate URL contains simple-form-demo
            Assert.assertTrue(driver.getCurrentUrl().contains("simple-form-demo"),
                    "URL does not contain 'simple-form-demo'");

            SimpleFormPage page = new SimpleFormPage(driver);

            String message = "Welcome to LambdaTest";
            page.enterMessage(message);
            page.clickShowMessage();

            // small wait for UI update (replace with explicit wait if needed)
            Thread.sleep(800);

            String shown = page.getDisplayedMessage();
            Assert.assertEquals(shown, message, "Displayed message mismatch");

        } finally {
            printSessionId(driver); // important: include in submission
            driver.quit();
        }
    }
}