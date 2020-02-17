package test.java.YandexDiskTests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import main.java.yandex.product.disk.pages.NavigationBlockPage;
import main.java.yandex.product.disk.pages.WordEditorPage;
import main.java.yandex.product.disk.pages.YandexDiskFilesPage;
import main.java.yandex.product.disk.pages.YandexDiskLoginPage;
import main.java.yandex.product.disk.service.BrowserParamsService;
import main.java.Framework.bo.UserCreator;

import static main.java.yandex.product.disk.pages.YandexDiskFilesPage.checkFolderName;
import static main.java.yandex.product.disk.service.NavigationBlockService.titlesComparisonInNavBlock;

public class YandexDiskTests extends CommonConditions {

    @BeforeClass(alwaysRun = true)
    public void logIn() {
        NavigationBlockPage loginPage = new YandexDiskLoginPage()
                .userLogin(UserCreator.fromPropertyCredentialsUser());
    }

    @Test(description = "Yandex disk navigation block test")
    public void yandexDiskNavigationBlockTest() {
        NavigationBlockPage navBlock = new NavigationBlockPage()
                .checkLastFilesBlock()
                .checkFilesBlock()
                .checkFotoBlock()
                .checkAlbumsBlock()
                .checkSharedBlock()
                .checkHistoryBlock()
                .checkArchiveBlock()
                .checkGarbageBlock();

        Assert.assertTrue(titlesComparisonInNavBlock());
    }

    @Test(dependsOnMethods = {"yandexDiskNavigationBlockTest"}, description = "Creation folder test")
    public void yandexDiscCreateFolderTest() {
        NavigationBlockPage newFolderTest = new NavigationBlockPage()
                .checkFilesBlock();
        YandexDiskFilesPage filesPage = new YandexDiskFilesPage()
                .openContextMenu()
                .createNewFolder()
                .setNewFolderName()
                .openCreatedFolder();

        Assert.assertTrue(checkFolderName());
    }

    @Test(dependsOnMethods = {"yandexDiscCreateFolderTest"}, description = "Creation Word file test")
    public void yandexDiskCreateFolderAndWordFileTest() {
        NavigationBlockPage newFolderTest = new NavigationBlockPage()
                .checkFilesBlock();
        YandexDiskFilesPage filesPage = new YandexDiskFilesPage()
                .openContextMenu()
                .createNewFolder()
                .setNewFolderName()
                .openCreatedFolder()
                .openContextMenu();
        WordEditorPage wordEditorPage = new YandexDiskFilesPage()
                .createWordFile();
        BrowserParamsService browserParamsService = new BrowserParamsService()
                .getAllOppenedTabs()
                .switchTab(1);
        wordEditorPage
                .sendTextToWordEditor()
                .setWordFileName();
        browserParamsService
                .switchTab(0);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(YandexDiskFilesPage.isWordFileExist());

        filesPage
                .checkWordFile();

        browserParamsService
                .getAllOppenedTabs()
                .switchTab(2);

        softAssert.assertTrue(wordEditorPage.getTextFromWordEditor());
        browserParamsService.switchTab(0);
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"yandexDiskCreateFolderAndWordFileTest"}, description = "Yandex disc garbage test")
    public void yandexDisсGarbageTest() {
        new BrowserParamsService()
                .getAllOppenedTabs()
                .switchTab(0);
        NavigationBlockPage filesBlock = new NavigationBlockPage()
                .checkFilesBlock();
        YandexDiskFilesPage filesPage = new YandexDiskFilesPage()
                .setNameToFolderMustBeOpened("test")
                .openCreatedFolder()
                .selectWordFile()
                .deleteWordFile();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(YandexDiskFilesPage.isFileNotInFilesFolder());

        filesBlock
                .checkGarbageBlock();

        softAssert.assertTrue(YandexDiskFilesPage.isFileInGarbage());

        filesPage
                .clearGarbage()
        ;
        softAssert.assertFalse(YandexDiskFilesPage.isFileNotInGarbage());
        softAssert.assertAll();
    }
}