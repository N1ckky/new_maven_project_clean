package main.java.Framework.ui;

import main.java.Framework.runner.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static main.java.yandex.product.disk.Configs.WAIT_TIMEOUT_SECONDS;


public class BrowserFactory {
    private static String port = Parameters.instance().getPort();
    private static String host = Parameters.instance().getHost();
    private static boolean grid = Boolean.parseBoolean(Parameters.instance().getGrid().toString());

    private BrowserFactory() {
    }

    public static WebDriver getBrowser() {
        WebDriver webDriver = null;
        switch (Parameters.instance().getBrowserType()) {
            case CHROME:
                if (!grid) {
                    WebDriverManager.chromedriver().setup();
                    webDriver = new ChromeDriver(OptionsManager.getChromeOptions());
                    configure(webDriver);
                } else {
                    ChromeOptions capabilities = new ChromeOptions();
                    try {
                        webDriver = new RemoteWebDriver(new URL("http://" + host + ":" + port + "/wd/hub"), capabilities);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case FIREFOX:
                if (!grid) {
                    WebDriverManager.firefoxdriver().setup();
                    webDriver = new FirefoxDriver(OptionsManager.getFirefoxOptions());
                    configure(webDriver);
                } else {
                    DesiredCapabilities capabilitiesF = DesiredCapabilities.firefox();
                    try {
                        webDriver = new RemoteWebDriver(new URL("http://" + host + ":" + port + "/wd/hub"), capabilitiesF);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver(OptionsManager.getChromeOptions());
                configure(webDriver);
                break;
        }
        return webDriver;
    }

    private static void configure(WebDriver webDriver) {
        webDriver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(Parameters.instance().getBrowserType().toString());
        capabilities.setPlatform(Platform.getCurrent());
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        capabilities.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
    }
}
