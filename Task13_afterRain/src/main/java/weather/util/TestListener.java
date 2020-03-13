package main.java.weather.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private Logger logger = LogManager.getRootLogger();

    public void onTestStart(ITestResult iTestResult) {
        logger.trace("Test started");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        logger.trace("Test success finished");
    }

    public void onTestFailure(ITestResult iTestResult) {
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
}