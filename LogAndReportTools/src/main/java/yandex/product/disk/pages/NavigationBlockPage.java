package main.java.yandex.product.disk.pages;

import org.openqa.selenium.By;

import java.util.LinkedHashSet;
import java.util.Set;

import static main.java.Framework.ui.Browser.getInstance;

public class NavigationBlockPage extends AbstractPage {
    public static Set<String> getRealTitlesOfBlock = new LinkedHashSet<>();

    private static final By FOLDER_TITLE_LOCATOR = By.xpath("//*[contains(@class,'listing-stub__desc')]//child::h1");
    private static final By LAST_FILES_LOCATOR = By.xpath("//*[@title='Последние']");
    private static final By LAST_FILES_TITLE_LOCATOR = By.xpath("//*[@title='Последние файлы']");
    private static final By FILES_LOCATOR = By.xpath("//*[@title='Файлы']");
    private static final By FILES_TITLE_LOCATOR = By.xpath("//*[@class='listing-heading__title']");
    private static final By FOTO_LOCATOR = By.xpath("//*[@title='Фото']");
    private static final By ALBUMS_LOCATOR = By.xpath("//*[@title='Альбомы']");
    private static final By ALBUMS_TITLE_LOCATOR = By.xpath("//*[contains(@class,'root__content-inner_page_albums')]//*[contains(text(),'Альбомы')]");
    private static final By SHARED_LOCATOR = By.xpath("//*[@title='Общий доступ']");
    private static final By HISTORY_LOCATOR = By.xpath("//*[@title='История']");
    private static final By HISTORY_BLOCK_TITLE_LOCATOR = By.xpath("//*[contains(@class,'journal-filter')]//child::h1");
    private static final By archiveLocator = By.xpath("//*[@title='Архив']");
    private static final By archiveBlockTitleLocator = By.xpath("//*[contains(@class,'listing-heading')]//child::h1");
    private static final By garbageLocator = By.xpath("//*[@id='/trash']");
    private static final By garbageBlockTitleLocator = By.xpath("//*[contains(@class,'listing-heading')]//child::h1");

    public NavigationBlockPage() {
        super();
    }

    public NavigationBlockPage checkLastFilesBlock() {
        selectFolderAndGetBlockTitle(LAST_FILES_LOCATOR, LAST_FILES_TITLE_LOCATOR);
        return this;
    }

    public NavigationBlockPage checkFilesBlock() {
        selectFolderAndGetBlockTitle(FILES_LOCATOR, FILES_TITLE_LOCATOR);
        return this;
    }

    public NavigationBlockPage checkFotoBlock() {
        selectFolderAndGetBlockTitle(FOTO_LOCATOR, FOLDER_TITLE_LOCATOR);
        return this;
    }

    public NavigationBlockPage checkAlbumsBlock() {
        selectFolderAndGetBlockTitle(ALBUMS_LOCATOR, ALBUMS_TITLE_LOCATOR);
        return this;
    }

    public NavigationBlockPage checkSharedBlock() {
        selectFolderAndGetBlockTitle(SHARED_LOCATOR, FOLDER_TITLE_LOCATOR);
        return this;
    }

    public NavigationBlockPage checkHistoryBlock() {
        selectFolderAndGetBlockTitle(HISTORY_LOCATOR, HISTORY_BLOCK_TITLE_LOCATOR);
        return this;
    }

    public NavigationBlockPage checkArchiveBlock() {
        selectFolderAndGetBlockTitle(archiveLocator, archiveBlockTitleLocator);
        return this;
    }

    public NavigationBlockPage checkGarbageBlock() {
        selectFolderAndGetBlockTitle(garbageLocator, garbageBlockTitleLocator);
        return this;
    }

    private void selectFolderAndGetBlockTitle(By folderLocator, By titleLocator) {
        getInstance().click(folderLocator);
        getRealTitlesOfBlock.add(getInstance().waitElementToBeClickable(titleLocator).getText());
    }
}