package main.java.yandex.product.disk.pages;

import main.java.Framework.bo.User;
import main.java.Framework.logger.Log;
import org.openqa.selenium.By;

import static main.java.Framework.ui.Browser.getInstance;

public class YandexDiskLoginPage extends AbstractPage {
    private static final By LOGIN_BUTTON_LOCATOR = By.xpath("//*[contains(@class,'button_login')]");
    private static final By LOGIN_INPUT_FIELD_LOCATOR = By.xpath("//*[@id='passp-field-login']");
    private static final By PASSWORD_INPUT_FIELD_LOCATOR = By.xpath("//*[@id='passp-field-passwd']");
    private static final By PASSWORD_BUTTON_LOCATOR = By.xpath("//*[(contains(@class,'passp-button passp-sign-in-button'))]");
    private static final By SUBMIT_BUTTON_LOCATOR = By.xpath("//*[(contains(@class,'button2_type_submit'))]");

    public YandexDiskLoginPage() {
        super();
    }

    public NavigationBlockPage userLogin(User user) {
        open().typeLogin(user).typePassowrd(user).clickSubmitButton();
        Log.info("Successful log in");

        return new NavigationBlockPage();
    }

    public YandexDiskLoginPage open() {
        getInstance().get(HOMEPAGE_URL);
        getInstance().click(LOGIN_BUTTON_LOCATOR);
        Log.info("Open login page");
        return this;
    }

    public YandexDiskLoginPage typeLogin(User user) {
        getInstance().type(LOGIN_INPUT_FIELD_LOCATOR,user.getUserLogin());
        getInstance().click(PASSWORD_BUTTON_LOCATOR);
        Log.info("Type login");
        return this;
    }

    public YandexDiskLoginPage typePassowrd(User user) {
        getInstance().type(PASSWORD_INPUT_FIELD_LOCATOR, user.getUserPassword());
        Log.info("Type password");
        return this;
    }
    public YandexDiskLoginPage clickSubmitButton() {
        getInstance().click(SUBMIT_BUTTON_LOCATOR);
        Log.info("Click submit button");
        return this;
    }
}