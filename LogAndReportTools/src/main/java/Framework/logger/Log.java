package main.java.Framework.logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log {
    public static Logger logger = LogManager.getLogger("myLogger");

    public static void info(String message){
        logger.info(message);
    }

    public static void debug(String message){
        logger.debug(message);
    }

    public static void error(String message){
        logger.error(message);
    }
}
