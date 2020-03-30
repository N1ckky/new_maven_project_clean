package android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

import static android.calculator.Configs.*;

public class CommonConditions {

    DesiredCapabilities capabilities;
    AppiumDriver<WebElement> driver = null;

    CommonConditions() {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AUTOMATION_NAME);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        capabilities.setCapability("chromedriverExecutableDir", CHROMEDRIVER_EXECUTABLE_DIR);
    }

    @BeforeTest
    public void before() throws MalformedURLException {
        driver = new AndroidDriver<>(new URL(String.format("http://%s/wd/hub", APPIUM_HUB)), capabilities);
    }

    @AfterTest
    public void after(){
        driver.quit();
    }
}
