package test.java.YandexDiskTests.steps;

import cucumber.api.java.en.*;
import main.java.Framework.bo.UserCreator;
import main.java.yandex.product.disk.pages.NavigationBlockPage;
import main.java.yandex.product.disk.pages.YandexDiskLoginPage;
import org.testng.Assert;

import static main.java.yandex.product.disk.service.NavigationBlockService.titlesComparisonInNavBlock;

public class NavigationBlockSteps {
    private NavigationBlockPage navigationBlockPage;

    @Given("^User login to Yandex disk$")
    public void userLoginToYandexDisk() {
        navigationBlockPage = new YandexDiskLoginPage().userLogin(UserCreator.fromPropertyCredentialsUser());
    }

    @When("^User open Last Files block$")
    public void userOpenBlock() {
        navigationBlockPage.checkLastFilesBlock();
    }

    @And("^User open Files block$")
    public void userOpenFilesBlock() {
        navigationBlockPage.checkFilesBlock();
    }

    @And("^User open Foto block$")
    public void userOpenFotoBlock() {
        navigationBlockPage.checkFotoBlock();
    }

    @And("^User open Albums block$")
    public void userOpenAlbumsBlock() {
        navigationBlockPage.checkAlbumsBlock();
    }

    @And("^User open Shared block$")
    public void userOpenSharedBlock() {
        navigationBlockPage.checkSharedBlock();
    }

    @And("^User open History block$")
    public void userOpenHistoryBlock() {
        navigationBlockPage.checkHistoryBlock();
    }

    @And("^User open Archive block$")
    public void userOpenArchiveBlock() {
        navigationBlockPage.checkArchiveBlock();
    }

    @And("^User open Garbage block$")
    public void userOpenGarbageBlock() {
        navigationBlockPage.checkGarbageBlock();
    }

    @Then("^Check that all folders in navigation block were opened$")
    public void checkThatAllFoldersInNavigationBlockWasOpened() {
        Assert.assertTrue(titlesComparisonInNavBlock(),"Some titles in Navigation block aren't as expected");
    }
}
