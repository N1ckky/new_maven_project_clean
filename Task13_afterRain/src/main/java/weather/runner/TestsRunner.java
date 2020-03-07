package main.java.weather.runner;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import main.java.weather.logger.Log;
import org.apache.log4j.PropertyConfigurator;

public class TestsRunner {

    public static void main(String[] args) {
        Log.info("Parsing CLI");
        parseCLI(args);
        Log.info("Setting properties from file: "+Parameters.instance().getProperties());
        setProperties();
        Log.info("Starting suite: "+Parameters.instance().getSuite());
        SuiteFactory.setSuite().run();
        Log.info("Finishing");
    }

    private static void parseCLI(String[] args) {
        Log.info("Parsing clis by JCommander");
        JCommander jCommander = new JCommander(Parameters.instance());
        try {
            jCommander.parse(args);
        } catch (ParameterException e) {
            Log.error(e.getMessage());
            System.exit(1);
        }
        if (Parameters.instance().isHelp()) {
            jCommander.usage();
            System.exit(0);
        }
    }

    private static void setProperties() {
        PropertyConfigurator.configure(Parameters.instance().getProperties());
    }
}
