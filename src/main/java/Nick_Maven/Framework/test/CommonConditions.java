package Nick_Maven.Framework.test;

import Nick_Maven.Framework.driver.DriverSingleton;
import Nick_Maven.Framework.util.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.util.ArrayList;

@Listeners ({TestListener.class})

public class CommonConditions {
    protected WebDriver driver;
    protected static final int WAIT_TIMEOUT_SECONDS = 10;
    protected final String SEARCH_TEXT = "Google Cloud Platform Pricing Calculator";
    public static ArrayList<String> tempList = new ArrayList<>();
    private ArrayList<String> tabs;
    protected final Logger logger = LogManager.getRootLogger();

    @BeforeMethod()
    public void setUp() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    CommonConditions openNewTab() {
        ((JavascriptExecutor) driver)
                .executeScript("window.open('about:blank','_blank');");
        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        return this;
    }

    CommonConditions switchTab(int number) {
        driver.switchTo().window(tabs.get(number));
        return this;
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }
}