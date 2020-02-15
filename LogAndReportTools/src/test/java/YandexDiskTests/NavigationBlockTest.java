package test.java.YandexDiskTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import main.java.yandex.product.disk.pages.NavigationBlockPage;
import main.java.yandex.product.disk.pages.YandexDiskLoginPage;
import main.java.Framework.bo.UserCreator;

import static main.java.yandex.product.disk.service.NavigationBlockService.titlesComparisonInNavBlock;


public class NavigationBlockTest extends CommonConditions {

    @Test(description = "Yandex disk navigation block test")
    public void yandexDiskNavigationBlockTest() {
        NavigationBlockPage yandexDiskTest = new YandexDiskLoginPage()
                .userLogin(UserCreator.fromPropertyCredentialsUser())
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
}