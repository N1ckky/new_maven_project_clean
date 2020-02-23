package main.java.Framework.runner;

import main.java.Framework.ui.BrowserType;
import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.Parameter;

public final class Parameters {
    private static Parameters instance;

    @Parameter(names = {"--help", "-h"}, help = true, description = "Help")
    private boolean help;

    @Parameter(names = {"--browser", "-b"}, description = "Browser type",
            converter = BrowserTypeConverter.class, required = true)
    private BrowserType browserType = BrowserType.CHROME;

    @Parameter(names = {"--suite", "-s"}, description = "XML suite", converter = SuiteTypeConverter.class, required = false)
    private SuiteType suiteType = SuiteType.SMOKE;

    @Parameter(names = {"--smoke_suite", "-smk"}, description = "Smoke test suite")
    private String smokeSuite = "./src/main/resources/testng-smoke.xml";

    @Parameter(names = {"--all_suite", "-all"}, description = "All test suite")
    private String allSuite = "./src/main/resources/testng-all.xml";

    @Parameter(names = {"--parallelClasses_suite", "-pc"}, description = "All test suite in parallel classes")
    private String parallelClassesSuite = "./src/main/resources/testng-parallel_classes.xml";

    @Parameter(names = {"--parallelTests_suite", "-pt"}, description = "All test suite in parallel test")
    private String parallelTestsSuite = "./src/main/resources/testng-parallel_tests.xml";

    @Parameter(names = {"--properties", "-prop"}, description = "Log4j properties location")
    private String properties = "./src/main/resources/log4j.properties";

    private Parameters() {
    }

    public static synchronized Parameters instance() {
        if (instance == null) {
            instance = new Parameters();
        }
        return instance;
    }

    public boolean isHelp() {
        return help;
    }

    public BrowserType getBrowserType() {
        return browserType;
    }

    public SuiteType getSuiteType() {
        return suiteType;
    }

    public String getAllSuite() {
        return allSuite;
    }

    public String getSmokeSuite() {
        return smokeSuite;
    }

    public String getProperties() {
        return properties;
    }

    public String getParallelClassesSuite() {
        return parallelClassesSuite;
    }

    public String getParallelTestsSuiteSuite() {
        return parallelTestsSuite;
    }

    public static class SuiteTypeConverter implements IStringConverter<SuiteType> {
        public SuiteType convert(String s) {
            return SuiteType.valueOf((s.toUpperCase()));
        }
    }

    public static class BrowserTypeConverter implements IStringConverter<BrowserType> {
        public BrowserType convert(String s) {
            return BrowserType.valueOf(s.toUpperCase());
        }
    }
}
