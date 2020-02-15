package main.java.yandex.product.disk.pages;

import org.openqa.selenium.WebDriver;
import main.java.yandex.product.disk.Configs;

import static main.java.Framework.ui.Browser.getInstance;

public abstract class AbstractPage {
    protected static WebDriver driver;
    protected static final String HOMEPAGE_URL = Configs.HOMEPAGE_URL;

    protected AbstractPage() {
        driver = setUpDriver();
    }

    public static WebDriver setUpDriver() {
        driver = getInstance().getWrappedDriver();
        return driver;
    }
}