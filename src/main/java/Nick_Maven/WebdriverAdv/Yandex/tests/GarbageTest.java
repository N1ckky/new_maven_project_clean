package Nick_Maven.WebdriverAdv.Yandex.tests;

import Nick_Maven.WebdriverAdv.Yandex.pages.NavigationBlockPage;
import Nick_Maven.WebdriverAdv.Yandex.pages.YandexDiskFilesPage;
import Nick_Maven.WebdriverAdv.Yandex.pages.YandexDiskLoginPage;
import Nick_Maven.WebdriverAdv.Yandex.service.DriverService;
import Nick_Maven.WebdriverAdv.Yandex.service.UserCreator;
import Nick_Maven.WebdriverAdv.Yandex.service.YandexDiscService;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GarbageTest {
    private WebDriver driver;
    public static final int WAIT_TIMEOUT_SECONDS = 10;

    @Test(description = "Yandex disk garbage test")
    public void yandexDiskNavigationBlockTest() {
        NavigationBlockPage garbageDiskTest = new YandexDiskLoginPage()
                .userLogin(UserCreator.withCredentialsFromProperty())
                .checkFilesBlock();
        YandexDiskFilesPage filesPage = new YandexDiskFilesPage()
                .setNameToFolderMustBeOpened("test")
                .openCreatedFolder()
                .selectWordFile()
                .deleteWordFile()
                ;
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertFalse(YandexDiscService.checkFileNotInFilesFolder());

        garbageDiskTest
                .checkGarbageBlock();

        softAssert.assertTrue(YandexDiscService.checkFileIsInGarbage());

        filesPage
                .clearGarbage()
        ;
        softAssert.assertFalse(YandexDiscService.checkFileNotInGarbage());
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void browserQuit() {
        driver = DriverService.getDriver();
        driver.quit();
        driver = null;
    }
}