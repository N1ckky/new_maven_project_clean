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

    @Parameter(names = {"--properties", "-prop"}, description = "Log4j properties location")
    private String properties = "./src/main/resources/log4j.properties";

    @Parameter(names = {"--grid", "-grid"}, description = "Use selenium grid?", converter = GridConverter.class)
    private GridType grid = GridType.FALSE;

    @Parameter(names = {"--host", "-host"}, description = "Selenium grid host")
    private String host = "localhost";

    @Parameter(names = {"--port", "-port"}, description = "Select node port")
    private String port = "4444";

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

    public String getSmokeSuite() {
        return smokeSuite;
    }

    public String getProperties() {
        return properties;
    }

    public GridType getGrid() {
        return grid;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
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

    public static class GridConverter implements IStringConverter<GridType>{
        public GridType convert(String s) {
            return GridType.valueOf(s.toUpperCase());
        }
    }
}
