package test.java.YandexDiskTests.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import main.java.yandex.product.disk.pages.YandexDiskFilesPage;
import org.testng.asserts.SoftAssert;

public class GarbageSteps {
    SoftAssert softAssert = new SoftAssert();

    @When("^User (?:delete|move) file to garbage$")
    public void userDeleteFileToGarbage() {
        new YandexDiskFilesPage()
                .setNameToFolderMustBeOpened("test")
                .openCreatedFolder()
                .selectWordFile()
                .deleteWordFile();
    }

    @Then("^Check that file is not in current folder$")
    public void checkThatFileIsNotInCurrentFolder() {
        softAssert.assertFalse(YandexDiskFilesPage.isFileNotInFilesFolder());
    }

    @And("^User (?:clear|erase|wipe) garbage$")
    public void userClearGarbage() {
        new YandexDiskFilesPage()
                .clearGarbage();
    }

    @Then("^Check that file not in garbage$")
    public void checkThatFileNotInGarbage() {
        softAssert.assertFalse(YandexDiskFilesPage.isFileNotInGarbage());
        softAssert.assertAll();
    }

    @Then("^Check that file is in current folder$")
    public void checkThatFileIsInCurrentFolder() {
        softAssert.assertTrue(YandexDiskFilesPage.isFileInGarbage());
    }
}
