package test.java.YandexDiskTests;

import main.java.Framework.util.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import static main.java.Framework.ui.Browser.getInstance;

@Listeners({TestListener.class})

public class CommonConditions {
    protected static WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void driverSetup() {
        driver = getInstance().getWrappedDriver();
    }

    @AfterClass(alwaysRun = true)
    public static void driverQuit() {
        getInstance().stopBrowser();
    }
}