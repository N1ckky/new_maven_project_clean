package main.java.Framework.runner;

import org.testng.TestNG;

import java.util.Collections;


public class SuiteFactory {
    private SuiteFactory(){

    }
    public static TestNG getSuite() {
        TestNG testNG = new TestNG();
        switch (Parameters.instance().getSuiteType()) {
            case SMOKE:
                testNG.setTestSuites(Collections.singletonList(Parameters.instance().getSmokeSuite()));
                break;
            default:
                testNG.setTestSuites(Collections.singletonList(Parameters.instance().getSmokeSuite()));
                break;
        }
        return testNG;
    }
}
