package test.java.YandexDiskTests.steps;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.en.*;
import main.java.Framework.bo.User;
import main.java.Framework.bo.UserCreator;
import main.java.Framework.logger.Log;
import main.java.yandex.product.disk.pages.YandexDiskLoginPage;
import org.testng.Assert;

import java.util.List;

import static main.java.Framework.ui.Browser.getInstance;
import static main.java.yandex.product.disk.Configs.USER_LOGIN;
import static main.java.yandex.product.disk.Configs.USER_PASWD;

public class LoginSteps {
    private User user;
    private YandexDiskLoginPage yandexDiskLoginPage = new YandexDiskLoginPage();

    @Given("User (?:is at|on the|on) login page")
    public void userIsAtLoginPage() {
        yandexDiskLoginPage.open();
    }

    @And("^User (?:has|use|enter) (?:valid|real|right) credentials$")
    public void userHasValidCredentials() {
        user = UserCreator.fromPropertyCredentialsUser();
    }

    @When("^User enter login$")
    public void userEnterLogin() {
        yandexDiskLoginPage.typeLogin(user);
    }

    @And("^User enter password$")
    public void userEnterPassword() {
        yandexDiskLoginPage.typePassowrd(user);
    }

    @And("^User click login button$")
    public void userClickLoginButton() {
        yandexDiskLoginPage.clickSubmitButton();
    }

    @Then("^User sees main (?:page|screen)$")
    public void userSeesMainPage() {
        getInstance().waitUntilDocumentIsReady();
        Assert.assertTrue(getInstance().getCurrentURL().contains("https://disk.yandex.by/client/disk"),
                "User is not at home page");
    }

    @Given("^User (?:has|use|enter) (?:invalid|bad|wrong|fake) credentials$")
    public void userHasInvalidCredentials() {
        user = UserCreator.withOwnCredentialsUser("", "");
    }

    @When("^User enter (.*) login$")
    public void userEnterLoginFromString(String login) {
        if (login.equals("valid_login")) {
            user.setUserLogin(USER_LOGIN);
        } else user.setUserLogin(login);
        yandexDiskLoginPage.typeLogin(user);
    }

    @Given("^Users with invalid credentials$")
    public void usersWithInvalidCredentials(DataTable dataTable) {
        List<String> usersList = dataTable.asList(String.class);
        user = UserCreator.withOwnCredentialsUser(usersList.get(0), usersList.get(1));
    }

    @And("^User enter (.*) password$")
    public void userEnterPasswordFromString(String password) {
        if (user.getUserLogin().equals(USER_LOGIN)) {
            if (password.equals("valid_password")) {
                user.setUserPassword(USER_PASWD);
            } else user.setUserPassword(password);
            yandexDiskLoginPage.typePassowrd(user);
        } else Log.info("Didn't type password, because login is invalid");
    }

    @Then("^User sees error message$")
    public void userSeesErrorMessage() {
        Assert.assertFalse(getInstance().getCurrentURL().contains("https://disk.yandex.by/client/disk"),
                "User is at home page");
    }

    @After
    public static void closeBrowser() {
        Log.info("Stops the browser");
        getInstance().stopBrowser();
    }
}
