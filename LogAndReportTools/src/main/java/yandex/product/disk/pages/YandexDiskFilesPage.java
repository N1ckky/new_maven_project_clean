package main.java.yandex.product.disk.pages;

import main.java.Framework.logger.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import main.java.yandex.product.disk.Configs;

import static main.java.Framework.ui.Browser.getInstance;
import static main.java.yandex.product.disk.service.RandomStringGenerator.randomStringGenerator;

public class YandexDiskFilesPage extends AbstractPage {
    private Actions actions;
    public static String folderName;
    public static String realFolderName;
    private static final String WORD_FILE_NAME = Configs.WORD_FILE_NAME;
    public static String FILE_LOCATOR_PATTERN = String.format("//*[@class='clamped-text' and contains(text(),'%s')]", WORD_FILE_NAME);

    private static final By SCREEN_FIELD_LOCATOR = By.xpath("//*[contains(@class,'root__content-inner_page_listing')]");
    private static final By NEW_FOLDER_FIELD_LOCATOR = By.xpath("//*[contains(@class,'context-menu-create-popup__item_new-folder')]");
    private static final By NEW_FOLDER_NAME_FIELD_LOCATOR = By.xpath("//*[@class='rename-dialog__rename-form']//child::input");
    private static final By NEW_FOLDER_CREATE_CONFIRM_BUTTON = By.xpath("//*[contains(@class,'confirmation-dialog__button_submit')]");
    private static final By NEW_WORD_FILE_FIELD_LOCATOR = By.xpath("//*[contains(@class,'context-menu-create-popup__item_word')]");
    private static final By GARBAGE_LOCATOR = By.xpath("//*[@title='Корзина']");
    private static final By CLEAR_GARBAGE_LOCATOR = By.xpath("//button[contains(@class,'client-listing__clean-trash-button')]");
    private static final By CLEAR_GARBAGE_CONFIRM_BUTTON_LOCATOR = By.xpath("//button[contains(@class,'button2_action_yes')]//child::span[contains(text(),'Очистить')]");

    public YandexDiskFilesPage() {
        super();
        actions = new Actions(driver);
        driver = getInstance().getWrappedDriver();
    }

    public YandexDiskFilesPage openContextMenu() {
        getInstance().openContextMenu(SCREEN_FIELD_LOCATOR);
        return this;
    }

    public YandexDiskFilesPage createNewFolder() {
        getInstance().click(NEW_FOLDER_FIELD_LOCATOR);
        return this;
    }

    public YandexDiskFilesPage setNewFolderName() {
        folderName = randomStringGenerator();
        getInstance().clear(NEW_FOLDER_NAME_FIELD_LOCATOR);
        getInstance().type(NEW_FOLDER_NAME_FIELD_LOCATOR, folderName);
        getInstance().click(NEW_FOLDER_CREATE_CONFIRM_BUTTON);
        Log.debug("Folder with name: " + folderName + " is created");
        return this;
    }

    public YandexDiskFilesPage openCreatedFolder() {
        By searchFolderNameFieldLocatorString = By.xpath(String.format("//*[@class='listing__items']//*[text()= '%s']", folderName));
        By currentFolderTitleLocatorString = By.xpath(String.format("//*[@title='%s']", folderName));
        getInstance().doubleClick(searchFolderNameFieldLocatorString);
        realFolderName = getInstance().getText(currentFolderTitleLocatorString);
        return this;
    }

    public YandexDiskFilesPage setNameToFolderMustBeOpened(String myFolderName) {
        folderName = myFolderName;
        return this;
    }

    public WordEditorPage createWordFile() {
        getInstance().click(NEW_WORD_FILE_FIELD_LOCATOR);
        Log.info("Word file created");
        return new WordEditorPage();
    }

    public WordEditorPage checkWordFile() {
        getInstance().waitUntilDocumentIsReady();
        By searchWordFileLocator = By.xpath(FILE_LOCATOR_PATTERN);
        getInstance().doubleClick(searchWordFileLocator);
        Log.debug("Word file: " + WORD_FILE_NAME + " oppened");
        return new WordEditorPage();
    }

    public YandexDiskFilesPage selectWordFile() {
        getInstance().waitUntilDocumentIsReady();
        By searchWordFileLocator = By.xpath(FILE_LOCATOR_PATTERN);
        Log.debug("Searching word file is exist: " + getInstance().waitElementToBeClickable(searchWordFileLocator));
        return new YandexDiskFilesPage();
    }

    public YandexDiskFilesPage deleteWordFile() {
        By searchWordFileLocator = By.xpath(String.format("//*[@class='clamped-text' and contains(text(),'%s')]" +
                "//ancestor::div[contains(@class,'listing-item_type_file')]//child::img", WORD_FILE_NAME));
        actions.moveToElement(getInstance().waitElementToBeClickable(searchWordFileLocator))
                .clickAndHold().moveToElement(getInstance().waitElementToBeClickable(GARBAGE_LOCATOR))
                .release().build().perform();
        return new YandexDiskFilesPage();
    }

    public YandexDiskFilesPage clearGarbage() {
        getInstance().click(CLEAR_GARBAGE_LOCATOR);
        getInstance().waitUntilDocumentIsReady();
        actions.moveToElement(getInstance().waitElementToBeClickable(CLEAR_GARBAGE_CONFIRM_BUTTON_LOCATOR))
                .click().build().perform();
        getInstance().refreshBrowser();
        getInstance().waitUntilDocumentIsReady();
        driver.navigate().refresh();
        return new YandexDiskFilesPage();
    }
}