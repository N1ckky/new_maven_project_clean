package main.java.yandex.product.disk.pages;

import main.java.Framework.logger.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import static main.java.Framework.ui.Browser.getInstance;
import static main.java.yandex.product.disk.Configs.TEXT_SENDING_TO_WORD_EDITOR;
import static main.java.yandex.product.disk.Configs.WORD_FILE_NAME;

public class WordEditorPage extends AbstractPage {
    private static final By EDITOR_FIELD_LOCATOR = By.xpath("//*[@id='WACViewPanel_EditingElement']");
    private static final By FILE_MENU_FIELD_LOCATOR = By.xpath("//*[@type='button' and contains(@data-unique-id,'FileMenu')]");
    private static final By FILE_MAIN_FIELD_LOCATOR = By.xpath("//*[@type='button' and @id='Home']");
    private static final By RENAME_OPTION_LOCATOR = By.xpath("//*[@id='jbtnRenameDialog-Menu48']");
    private static final By FILE_NAME_FIELD_LOCATOR = By.xpath("//*[@id='txtDocumentName']");
    private static final By BUTTON_OK_LOCATOR = By.xpath("//*[@id='WACDialogActionButton']");
    private static final By EDITOR_FIELD_BY_ID_LOCATOR = By.id("WACViewPanel_EditingElement");

    private Actions actions = new Actions(getInstance().getWrappedDriver());

    public WordEditorPage() {
        super();
    }

    public WordEditorPage sendTextToWordEditor() {
        getInstance().switchToFrame(0);
        getInstance().type(EDITOR_FIELD_LOCATOR, TEXT_SENDING_TO_WORD_EDITOR);
        Log.info("Text to word sended");
        return this;
    }

    public WordEditorPage setWordFileName() {
        getInstance().waitUntilDocumentIsReady();
        actions.moveToElement(getInstance().waitElementToBeClickable(FILE_MAIN_FIELD_LOCATOR)).click().build().perform();
        getInstance().waitUntilDocumentIsReady();
        actions.moveToElement(getInstance().waitElementToBeClickable(FILE_MENU_FIELD_LOCATOR)).click().build().perform();
        actions.moveToElement(getInstance().waitElementToBeClickable(RENAME_OPTION_LOCATOR)).click().build().perform();
        getInstance().waitUntilDocumentIsReady();
        getInstance().clear(FILE_NAME_FIELD_LOCATOR);
        getInstance().type(FILE_NAME_FIELD_LOCATOR, WORD_FILE_NAME);
        getInstance().click(BUTTON_OK_LOCATOR);
        return this;
    }

    public static boolean getTextFromWordEditor() {
        getInstance().waitUntilDocumentIsReady();
        getInstance().switchToFrame(0);
        if (!getInstance().isVisible(EDITOR_FIELD_BY_ID_LOCATOR)) {
            getInstance().refreshBrowser();
            getInstance().waitUntilDocumentIsReady();
        }
        getInstance().waitPresenceOfElement(EDITOR_FIELD_BY_ID_LOCATOR);
        String textFromEditor = getInstance().getValue(getInstance().waitForVisibilityOfElement(EDITOR_FIELD_BY_ID_LOCATOR));
        if (textFromEditor.contains("’")) {
            textFromEditor = (textFromEditor.substring(1, textFromEditor.length() - 1)).replace('’', '\'');
        } else textFromEditor = (textFromEditor.substring(1, textFromEditor.length() - 1));
        Log.debug("Text sended to WORD editor: " + TEXT_SENDING_TO_WORD_EDITOR);
        Log.debug("Text received to WORD editor: " + textFromEditor);
        Log.info("Sended text and text from word are equals: " + TEXT_SENDING_TO_WORD_EDITOR.equals(textFromEditor));
        return TEXT_SENDING_TO_WORD_EDITOR.equals(textFromEditor);
    }
}