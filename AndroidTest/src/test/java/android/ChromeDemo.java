package android;

import android.calculator.logger.Log;
import io.appium.java_client.remote.MobileCapabilityType;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static android.calculator.Configs.BROWSER_NAME;

public class ChromeDemo extends CommonConditions {


    public ChromeDemo() {
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BROWSER_NAME);
    }


    @BeforeTest
    public void timeouts() {
        driver.manage().timeouts()
                .pageLoadTimeout(3, TimeUnit.MINUTES)
                .implicitlyWait(90, TimeUnit.SECONDS);
    }

    @Test
    public void demoTest() {
        final String epamUrl = "https://www.epam.com/";
        driver.get(epamUrl);
        Log.info("Url from chrome: " + driver.getCurrentUrl());
        Assert.assertEquals(epamUrl, driver.getCurrentUrl(), "Current url isn't correct");
    }
}
