package main.java.Framework.runner;

import org.testng.TestNG;
import java.util.ArrayList;
import java.util.List;

public class TestsRunner {
    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        List<String> file = new ArrayList<>();
        file.add("src\\main\\resources\\testng-smoke.xml");
        testNG.setTestSuites(file);
        testNG.run();
    }
}