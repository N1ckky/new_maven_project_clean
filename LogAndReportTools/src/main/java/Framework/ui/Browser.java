package main.java.Framework.ui;

import main.java.Framework.CustomConditions;
import main.java.Framework.logger.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static main.java.yandex.product.disk.Configs.WAIT_TIMEOUT_SECONDS;

public final class Browser implements WrapsDriver {
    private static ThreadLocal<Browser> instance = new ThreadLocal<>();

    private WebDriver wrappedWebDriver;

    private Browser() {
        WebDriverManager.chromedriver().setup();
        wrappedWebDriver = new ChromeDriver();
        wrappedWebDriver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        wrappedWebDriver.manage().window().maximize();
    }

    public static synchronized Browser getInstance() {
        if (instance.get() == null) {
            instance.set(new Browser());
        }
        return instance.get();
    }

    public WebDriver getWrappedDriver() {
        return wrappedWebDriver;
    }

    public void stopBrowser() {
        try {
            if (getWrappedDriver() != null) {
                getWrappedDriver().quit();
            }
        } catch (WebDriverException e) {
            Log.error(e.getMessage());
        } finally {
            instance.set(null);
        }
    }

    public void get(String url) {
        Log.debug("Webdriver opening url:" + url);
        wrappedWebDriver.get(url);
        new WebDriverWait(wrappedWebDriver, WAIT_TIMEOUT_SECONDS).until(CustomConditions.jsReadyStateCompleted());
        makeScreenshot();
    }

    public void click(By by) {
        Log.debug("Click " + by);
        WebElement element = waitElementToBeClickable(by);
        highlightBackgroundAndMakeScreenshot(element);
        element.click();
    }

    public void type(By by, String keys) {
        Log.debug("Type " + keys + " in text field" + by);
        WebElement element = waitForVisibilityOfElement(by);
        highlightBackgroundAndMakeScreenshot(element);
        element.sendKeys(keys);
    }

    public void clear(By by) {
        Log.debug("Clearing field" + by);
        WebElement element = waitForVisibilityOfElement(by);
        highlightBackground(element);
        wrappedWebDriver.findElement(by).sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a", Keys.DELETE));
    }

    public void doubleClick(By by) {
        Actions actions = new Actions(wrappedWebDriver);
        Log.debug("Double clicking element " + by);
        WebElement element = waitForVisibilityOfElement(by);
        highlightBackgroundAndMakeScreenshot(element);
        actions.doubleClick(element).perform();
    }

    public void sendKeys(By by, Keys keys) {
        Log.debug("Sending key " + keys + "to " + by);
        waitForVisibilityOfElement(by).sendKeys(keys);
    }

    public String getText(By by) {
        Log.debug("Getting text from element " + by);
        WebElement element = waitForVisibilityOfElement(by);
        highlightBackground(element);
        return element.getText();
    }

    public String getAttribute(By by, String attribute) {
        Log.debug("Getting attribute " + attribute + "from element " + by);
        return waitForVisibilityOfElement(by).getAttribute(attribute);
    }

    public void waitUntilUrlContains(String url) {
        Log.debug("Waiting until current url contains" + url);
        new WebDriverWait(wrappedWebDriver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.urlContains(url));
    }

    public void dragAndDrop(By objectToDrag, By objectToDropOn) {
        Actions actions = new Actions(wrappedWebDriver);
        Log.debug("Processing drag and drop on " + objectToDrag + "to " + objectToDropOn);
        actions.dragAndDrop(waitForVisibilityOfElement(objectToDrag), waitForVisibilityOfElement(objectToDropOn)).build().perform();
    }

    public void openContextMenu(By by) {
        Actions actions = new Actions(wrappedWebDriver);
        Log.debug("Move to element " + by);
        WebElement element = waitForVisibilityOfElement(by);
        actions.moveToElement(element).contextClick().build().perform();
    }

    public void waitUntilDocumentIsReady() {
        new WebDriverWait(wrappedWebDriver, WAIT_TIMEOUT_SECONDS)
                .until(CustomConditions.jQueryAJAXCallsHaveCompleted());
        new WebDriverWait(wrappedWebDriver, WAIT_TIMEOUT_SECONDS)
                .until(CustomConditions.jsReadyStateCompleted());
    }

    public void waitReadyState() {
        new WebDriverWait(wrappedWebDriver, WAIT_TIMEOUT_SECONDS)
                .until(CustomConditions.jsReadyStateCompleted());
    }

    public void waitAJAXCallsCompleted() {
        new WebDriverWait(wrappedWebDriver, WAIT_TIMEOUT_SECONDS)
                .until(CustomConditions.jQueryAJAXCallsHaveCompleted());
    }

    public void refreshBrowser() {
        wrappedWebDriver.navigate().refresh();
        waitUntilDocumentIsReady();
    }

    public void switchToAnotherTab() {
        for (String winHandle : wrappedWebDriver.getWindowHandles()) {
            wrappedWebDriver.switchTo().window(winHandle);
        }
        Log.debug("Switching to another browser tab");
    }

    public void switchToFrame(int index) {
        Log.debug("Switch to frame " + index);
        waitUntilDocumentIsReady();
        wrappedWebDriver.switchTo().frame(index);
    }

    public Set<String> getWindowHandles() {
        Log.debug("Getting current window handles");
        return wrappedWebDriver.getWindowHandles();
    }

    public void switchToWindow(String handle) {
        Log.debug("Switching to window with handles " + handle);
        wrappedWebDriver.switchTo().window(handle);
    }

    public boolean isVisible(By by) {
        try {
            waitForVisibilityOfElement(by);
        } catch (WebDriverException e) {
            return false;
        }
        return true;
    }

    public String getCurrentURL() {
        Log.debug("Getting current URL");
        return wrappedWebDriver.getCurrentUrl();
    }

    public WebElement waitForVisibilityOfElement(By by) {
        Log.debug("Waiting for visibility of element " + by);
        return new WebDriverWait(wrappedWebDriver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement waitElementToBeClickable(By by) {
        Log.debug("Waiting for element to be clickable " + by);
        return new WebDriverWait(wrappedWebDriver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement waitPresenceOfElement(By by) {
        Log.debug("Waiting for presence of element " + by);
        return new WebDriverWait(wrappedWebDriver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void makeScreenshot() {
        File screenshotFile = new File("logs/" + System.currentTimeMillis() + "screenshot.jpg");
        try {
            File screenshotAsFile = ((TakesScreenshot) wrappedWebDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotAsFile, screenshotFile);
            Log.info("Taken screenshot " + screenshotFile.getAbsolutePath());
        } catch (IOException e) {
            Log.error("Failed screenshot: " + e.getMessage());
        }
    }

    public void highlightBackground(WebElement element) {
        String backgroundColor = element.getCssValue("backgroundColor");
        JavascriptExecutor js = ((JavascriptExecutor) wrappedWebDriver);
        js.executeScript("arguments[0].style.backgroundColor = 'yellow'", element);
        js.executeScript("arguments[0].style.backgroundColor = '" + backgroundColor + "'", element);
    }

    public void highlightBackgroundAndMakeScreenshot(WebElement element) {
        String backgroundColor = element.getCssValue("backgroundColor");
        JavascriptExecutor js = ((JavascriptExecutor) wrappedWebDriver);
        js.executeScript("arguments[0].style.backgroundColor = 'yellow'", element);
        makeScreenshot();
        js.executeScript("arguments[0].style.backgroundColor = '" + backgroundColor + "'", element);
    }
}