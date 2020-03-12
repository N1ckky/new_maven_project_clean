package test.java.YandexDiskTests;

import main.java.Framework.bo.UserCreator;
import org.testng.Assert;
import org.testng.annotations.Test;
import main.java.yandex.product.disk.pages.YandexDiskLoginPage;

import static main.java.Framework.ui.Browser.getInstance;

public class SmokeTest extends CommonConditions {

    @Test(description = "Smoke test - log In to yandex Disc")
    public void yandexDiscSmokeTest() {
        new YandexDiskLoginPage()
                .userLogin(UserCreator.fromPropertyCredentialsUser());
        getInstance().waitUntilDocumentIsReady();
        Assert.assertTrue(getInstance().getCurrentURL().contains("https://disk.yandex.by/client/disk"),
                "User is not at home page");
    }
}