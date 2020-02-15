package main.java.yandex.product.disk.service;

import main.java.Framework.logger.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static main.java.Framework.ui.Browser.getInstance;
import static main.java.yandex.product.disk.Configs.TEXT_SENDING_TO_WORD_EDITOR;

public class WordOnlineService {
    public static boolean checkTextInTheWordDocument;
    private static final By EDITOR_FIELD_BY_ID_LOCATOR = By.id("WACViewPanel_EditingElement");


    protected static String getValue(WebElement element) {
        try {
            String value = (String) ((JavascriptExecutor) getInstance().getWrappedDriver()).executeScript("return arguments[0].textContent;", element);
            return value;
        } catch (Exception e) {
            return "can't get text";
        }
    }

    public static boolean checkTextInWordEditor() {
        getInstance().waitUntilDocumentIsReady();
        getInstance().switchToFrame(0);
        if (!getInstance().isVisible(EDITOR_FIELD_BY_ID_LOCATOR)) {
            getInstance().refreshBrowser();
            getInstance().waitUntilDocumentIsReady();
        }
        getInstance().waitPresenceOfElement(EDITOR_FIELD_BY_ID_LOCATOR);
        String textFromEditor = getValue(getInstance().waitForVisibilityOfElement(EDITOR_FIELD_BY_ID_LOCATOR));
        if (textFromEditor.contains("’")) {
            textFromEditor = (textFromEditor.substring(1, textFromEditor.length() - 1)).replace('’', '\'');
        } else textFromEditor = (textFromEditor.substring(1, textFromEditor.length() - 1));
        Log.debug("Text sended to WORD editor: " + TEXT_SENDING_TO_WORD_EDITOR);
        Log.debug("Text received to WORD editor: " + textFromEditor);
        checkTextInTheWordDocument = TEXT_SENDING_TO_WORD_EDITOR.equals(textFromEditor);
        Log.info("Sended text and text from word are equals: " + checkTextInTheWordDocument);
        return checkTextInTheWordDocument;
    }
}