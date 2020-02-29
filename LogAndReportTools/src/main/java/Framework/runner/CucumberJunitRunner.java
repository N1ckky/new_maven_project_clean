package main.java.Framework.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = "src/main/resources/features",
        glue = "test.java.YandexDiskTests.steps",
        tags = {"@login, @negativeLogin, @negativeLoginDT, @navigationBlock, @createFolderAndWordFile, @garbage"},
        plugin = {
                "pretty", "json:target/Cucumber.json",
                "html:targer/cucumber-html-report"
        }
)

public class CucumberJunitRunner {
}
