package com.lambdaTest.tests;

import com.lambdaTest.base.BaseTest;
import com.lambdaTest.pages.SliderPage;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SliderTest extends BaseTest {

    @Test(dataProvider = "ltCaps", dataProviderClass = BaseTest.class)
    public void dragAndDropSliderTo95(MutableCapabilities caps) throws Exception {
        WebDriver driver = createDriver(caps);
        try {
            driver.get("https://www.lambdatest.com/selenium-playground");
            driver.findElement(org.openqa.selenium.By.cssSelector("a[href*='drag-drop-range-slider']")).click();

            SliderPage sliderPage = new SliderPage(driver);

            // set to 95 (via JS to avoid flakiness)
            sliderPage.setSliderValueTo95();

            // small wait for UI update
            Thread.sleep(700);

            String value = sliderPage.getSliderValue();
            Assert.assertTrue(value.contains("95"), "Slider value is not 95, actual: " + value);

        } finally {
            printSessionId(driver);
            driver.quit();
        }
    }
}