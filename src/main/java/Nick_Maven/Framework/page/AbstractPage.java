package Nick_Maven.Framework.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {
    protected static WebDriver driver;
    protected static final int WAIT_TIMEOUT_SECONDS = 10;
    protected static final String JS_CLICK = "arguments[0].click();";

    protected abstract AbstractPage openPage();

    AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected final Logger logger = LogManager.getRootLogger();

    protected void jsExecutor(WebElement webElementForClick) {
        ((JavascriptExecutor) driver).executeScript(JS_CLICK, webElementForClick);
    }

    protected void jsExecutor(String scriptToPerfomAtElementString, WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript(scriptToPerfomAtElementString, webElement);
    }
    protected void jsExecutor(String scriptString) {
        ((JavascriptExecutor) driver).executeScript(scriptString);
    }
}