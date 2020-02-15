package main.java.Framework.customAppender;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.Reporter;

public class CustomAppender extends AppenderSkeleton {
    private static final String screenshotLocator = "Taken screenshot";
    private static final String screenshotJpgExtension = ".jpg";

    @Override
    protected void append(LoggingEvent event) {
        String message = changeScreenshotLink(event);
        Reporter.log(message);
    }

    private String changeScreenshotLink(LoggingEvent event) {
        String message = layout.format(event);
        String layoutPatternExtracted = layout.format(event);
        if (message.contains(screenshotLocator) && message.contains(screenshotJpgExtension)) {
            String extractedMessage = message.substring(message.indexOf(screenshotLocator), message.length() - 1);
            layoutPatternExtracted = layoutPatternExtracted.substring(0, layoutPatternExtracted.indexOf(screenshotLocator));
            message = extractedMessage.substring(0, screenshotLocator.length()) +
                    " <a href =\"file:///" + extractedMessage.substring(screenshotLocator.length() + 1) + "\" target=”blank”>screenshot</a>";
            return (layoutPatternExtracted + " " + message + "</br>");
        }
        return (message + "</br>");
    }

    @Override
    public void close() {
    }

    @Override
    public boolean requiresLayout() {
        return true;
    }
}