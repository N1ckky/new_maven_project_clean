package main.java.weather.runner;

import org.testng.TestNG;

import java.util.Collections;


public class SuiteFactory {
    private SuiteFactory() {

    }

    public static TestNG setSuite() {
        TestNG testNG = new TestNG();
        testNG.setTestSuites(Collections.singletonList(Parameters.instance().getSuite()));
        return testNG;
    }
}
