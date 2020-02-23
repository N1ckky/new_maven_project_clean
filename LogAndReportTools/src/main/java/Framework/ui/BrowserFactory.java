package main.java.Framework.ui;

import main.java.Framework.runner.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

import static main.java.yandex.product.disk.Configs.WAIT_TIMEOUT_SECONDS;


public class BrowserFactory {
    private BrowserFactory() {
    }

    public static WebDriver getBrowser() {
        WebDriver webDriver;

        switch (Parameters.instance().getBrowserType()) {
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver(OptionsManager.getFirefoxOptions());
                break;
            default:
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver(OptionsManager.getChromeOptions());
                break;
        }
        configure(webDriver);
        return webDriver;
    }

    private static void configure(WebDriver webDriver){
        webDriver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(Parameters.instance().getBrowserType().toString());
        capabilities.setPlatform(Platform.getCurrent());
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT,true);
        capabilities.setCapability(CapabilityType.SUPPORTS_ALERTS,true);
    }
}
