package test.java.YandexDiskTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import main.java.yandex.product.disk.pages.NavigationBlockPage;
import main.java.yandex.product.disk.pages.YandexDiskFilesPage;
import main.java.yandex.product.disk.pages.YandexDiskLoginPage;
import main.java.Framework.bo.UserCreator;

public class CreateFolderTest extends CommonConditions {
    @Test(description = "Creation folder test")
    public void yandexDiscCreateFolderTest() {
        NavigationBlockPage newFolderTest = new YandexDiskLoginPage()
                .userLogin(UserCreator.fromPropertyCredentialsUser())
                .checkFilesBlock();
        YandexDiskFilesPage filesPage = new YandexDiskFilesPage()
                .openContextMenu()
                .createNewFolder()
                .setNewFolderName()
                .openCreatedFolder()
                ;

        Assert.assertTrue(YandexDiskFilesPage.checkFolderName());
    }
}