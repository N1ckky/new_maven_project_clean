package test.java.YandexDiskTests;

import main.java.Framework.bo.UserCreator;
import org.testng.annotations.Test;
import main.java.yandex.product.disk.pages.NavigationBlockPage;
import main.java.yandex.product.disk.pages.YandexDiskLoginPage;

public class SmokeTest extends CommonConditions {

    @Test(description = "Smoke test - log In to yandex Disc")
    public void yandexDiscSmokeTest() {
        NavigationBlockPage yandexDiskTest = new YandexDiskLoginPage()
                .userLogin(UserCreator.fromPropertyCredentialsUser());
    }
}