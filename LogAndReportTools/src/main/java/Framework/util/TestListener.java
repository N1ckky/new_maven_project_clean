package main.java.Framework.util;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static main.java.Framework.ui.Browser.getInstance;


public class TestListener implements ITestListener {
    private Logger logger = LogManager.getRootLogger();

    public void onTestStart(ITestResult iTestResult) {
        logger.trace("Test started");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        logger.trace("Test success finished");
    }

    public void onTestFailure(ITestResult iTestResult) {
        saveScreenshot();
        logger.trace("Test failed!");
    }

    public void onTestSkipped(ITestResult iTestResult) {
        logger.trace("Test skiped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        logger.trace("Test failed, but with succes percentage");
    }

    public void onStart(ITestContext iTestContext) {
        logger.trace("Started");
    }

    public void onFinish(ITestContext iTestContext) {
        logger.trace("Finished");
    }

    private void saveScreenshot(){
        File screenCapture = ((TakesScreenshot) getInstance().getWrappedDriver())
                .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenCapture, new File(
                    ".//target/screenshots/"
                    + getCurrentTimeAsString() +
                    ".png"));
        } catch (IOException e) {
            logger.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

    private String getCurrentTimeAsString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "uuuu-MM-dd_HH-mm-ss" );
        return ZonedDateTime.now().format(formatter);
    }
}