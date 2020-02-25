package main.java.Framework.runner;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import main.java.Framework.logger.Log;
import org.apache.log4j.PropertyConfigurator;

public class TestsRunner {
    public static void main(String[] args) {
        Log.debug("Parsing CLI");
        parseCLI(args);
        Log.debug("Setting properties");
        setProperties();
        Log.debug("Starting");
        SuiteFactory.getSuite().run();
        Log.debug("Finishing");
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