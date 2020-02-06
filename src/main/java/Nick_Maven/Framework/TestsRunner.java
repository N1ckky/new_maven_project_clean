package Nick_Maven.Framework;

import org.testng.TestNG;
import java.util.ArrayList;
import java.util.List;

public class TestsRunner {
    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        List<String> file = new ArrayList<>();
        file.add("src\\main\\resources\\testng-all.xml");
        System.setProperty("browser", "chrome");
        System.setProperty("environment", "qa");
        System.setProperty("surefire.suiteXmlFiles", "src\\main\\resources\\testng-all.xml");
        testNG.setTestSuites(file);
        testNG.run();
    }
}

class SmokeTest {
    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        List<String> file = new ArrayList<>();
        file.add("src\\main\\resources\\testng-smoke.xml");
        System.setProperty("browser", "chrome");
        System.setProperty("environment", "dev");
        System.setProperty("surefire.suiteXmlFiles", "src\\main\\resources\\testng-smoke.xml");
        testNG.setTestSuites(file);
        testNG.run();
    }
}