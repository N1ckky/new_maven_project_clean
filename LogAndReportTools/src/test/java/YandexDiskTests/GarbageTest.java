package test.java.YandexDiskTests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import main.java.yandex.product.disk.pages.NavigationBlockPage;
import main.java.yandex.product.disk.pages.YandexDiskFilesPage;
import main.java.yandex.product.disk.pages.YandexDiskLoginPage;
import main.java.Framework.bo.UserCreator;

public class GarbageTest extends CommonConditions {

    @Test(description = "Yandex disk garbage test")
    public void yandexDiskNavigationBlockTest() {
        NavigationBlockPage garbageDiskTest = new YandexDiskLoginPage()
                .userLogin(UserCreator.fromPropertyCredentialsUser())
                .checkFilesBlock();
        YandexDiskFilesPage filesPage = new YandexDiskFilesPage()
                .setNameToFolderMustBeOpened("test")
                .openCreatedFolder()
                .selectWordFile()
                .deleteWordFile()
                ;
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertFalse(YandexDiskFilesPage.isFileNotInFilesFolder());

        garbageDiskTest
                .checkGarbageBlock();

        softAssert.assertTrue(YandexDiskFilesPage.isFileInGarbage());

        filesPage
                .clearGarbage()
        ;
        softAssert.assertFalse(YandexDiskFilesPage.isFileNotInGarbage());
        softAssert.assertAll();
    }
}