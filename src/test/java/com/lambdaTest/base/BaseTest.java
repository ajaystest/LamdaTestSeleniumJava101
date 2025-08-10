package com.lambdaTest.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.MutableCapabilities;
import org.testng.annotations.DataProvider;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected String username = System.getenv("LT_USERNAME");
    protected String accessKey = System.getenv("LT_ACCESS_KEY");

    @DataProvider(name = "ltCaps", parallel = true)
    public Object[][] ltCapabilities() {
        if (username == null || accessKey == null) {
            throw new RuntimeException("Please set LT_USERNAME and LT_ACCESS_KEY environment variables");
        }

        // First capability set: Windows 10 + Chrome (latest)
        MutableCapabilities caps1 = new MutableCapabilities();
        caps1.setCapability("browserName", "Chrome");
        caps1.setCapability("browserVersion", "latest");
        Map<String, Object> ltOptions1 = new HashMap<>();
        ltOptions1.put("platformName", "Windows 10");
        ltOptions1.put("user", username);
        ltOptions1.put("accessKey", accessKey);
        ltOptions1.put("build", "Selenium101-Assignment");
        ltOptions1.put("name", "Selenium101-Test");
        // enable logs & recordings
        ltOptions1.put("video", true);
        ltOptions1.put("network", true);
        ltOptions1.put("console", true);
        ltOptions1.put("visual", true);
        caps1.setCapability("LT:Options", ltOptions1);

        // Second capability set: macOS Catalina + Safari (as example)
        MutableCapabilities caps2 = new MutableCapabilities();
        caps2.setCapability("browserName", "Safari");
        caps2.setCapability("browserVersion", "latest");
        Map<String, Object> ltOptions2 = new HashMap<>(ltOptions1);
        ltOptions2.put("platformName", "macOS Catalina");
        ltOptions2.put("name", "Selenium101-Test-mac");
        caps2.setCapability("LT:Options", ltOptions2);

        return new Object[][] { { caps1 }, { caps2 } };
    }

    protected WebDriver createDriver(MutableCapabilities caps) throws Exception {
        String hubUrl = "https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";
        WebDriver driver = new RemoteWebDriver(new URL(hubUrl), caps);
        return driver;
    }

    protected void printSessionId(WebDriver driver) {
        if (driver instanceof RemoteWebDriver) {
            SessionId id = ((RemoteWebDriver) driver).getSessionId();
            System.out.println("== LambdaTest SessionID: " + (id == null ? "null" : id.toString()));
        }
    }
}