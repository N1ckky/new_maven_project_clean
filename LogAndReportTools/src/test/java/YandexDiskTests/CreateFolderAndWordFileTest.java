package test.java.YandexDiskTests;

import main.java.Framework.bo.User;
import main.java.Framework.bo.UserCreator;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import main.java.yandex.product.disk.pages.NavigationBlockPage;
import main.java.yandex.product.disk.pages.WordEditorPage;
import main.java.yandex.product.disk.pages.YandexDiskFilesPage;
import main.java.yandex.product.disk.pages.YandexDiskLoginPage;
import main.java.yandex.product.disk.service.BrowserParamsService;

import static main.java.yandex.product.disk.service.WordOnlineService.checkTextInWordEditor;
import static main.java.yandex.product.disk.service.YandexDiscService.checkFolderName;

public class CreateFolderAndWordFileTest extends CommonConditions {

    @Test(description = "Creation Word file test")
    public void yandexDiskCreateFolderAndWordFileTest() {
        BrowserParamsService browser = new BrowserParamsService();
        User testUser = UserCreator.fromPropertyCredentialsUser();

        NavigationBlockPage newFolderTest = new YandexDiskLoginPage()
                .userLogin(testUser)
                .checkFilesBlock();
        YandexDiskFilesPage filesPage = new YandexDiskFilesPage()
                .openContextMenu()
                .createNewFolder()
                .setNewFolderName()
                .openCreatedFolder()
                .openContextMenu();
        WordEditorPage wordEditorPage = new YandexDiskFilesPage()
                .createWordFile();
        browser
                .getAllOppenedTabs()
                .switchTab(1);
        wordEditorPage
                .sendTextToWordEditor()
                .setWordFileName();
        browser
                .switchTab(0);
        filesPage
                .checkWordFile();
        browser
                .getAllOppenedTabs()
                .switchTab(2);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(checkFolderName());
        softAssert.assertTrue(checkTextInWordEditor());
        softAssert.assertAll();
    }
}