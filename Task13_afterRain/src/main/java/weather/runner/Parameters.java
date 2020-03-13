package main.java.weather.runner;

import com.beust.jcommander.Parameter;

public final class Parameters {
    private static Parameters instance;

    @Parameter(names = {"--help", "-h"}, help = true, description = "Help")
    private boolean help;

    @Parameter(names = {"--suite", "-s"}, description = "XML suite")
    private String Suite = "./src/main/resources/testng.xml";

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

    public String getSuite() {
        return Suite;
    }

    public String getProperties() {
        return properties;
    }
}
