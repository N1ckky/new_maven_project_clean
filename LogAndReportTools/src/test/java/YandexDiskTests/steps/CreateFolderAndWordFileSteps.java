package test.java.YandexDiskTests.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import main.java.yandex.product.disk.pages.WordEditorPage;
import main.java.yandex.product.disk.pages.YandexDiskFilesPage;
import main.java.yandex.product.disk.service.BrowserParamsService;
import org.testng.asserts.SoftAssert;

import static main.java.yandex.product.disk.pages.YandexDiskFilesPage.checkFolderName;

public class CreateFolderAndWordFileSteps {
    private BrowserParamsService browser = new BrowserParamsService();
    private YandexDiskFilesPage filesPage = new YandexDiskFilesPage();
    private WordEditorPage wordEditorPage;
    SoftAssert softAssert = new SoftAssert();

    @And("^User create new folder with random name$")
    public void userCreateNewFolderWithRandomName() {
        filesPage.openContextMenu()
                .createNewFolder()
                .setNewFolderName();
    }

    @And("^User open created folder$")
    public void userOpenCreatedFolder() {
        filesPage.openCreatedFolder();
    }

    @And("^User create new Word file$")
    public void userCreateNewWordFile() {
        wordEditorPage = filesPage.openContextMenu()
                .createWordFile();
    }

    @And("^User open created Word file$")
    public void userOpenCreatedWordFile() {
        filesPage
                .checkWordFile();
    }

    @And("^User switch to (\\d+) tab in browser$")
    public void userSwitchNextTabInBrowser(int tabNumber) {
        browser.getAllOppenedTabs().switchTab(tabNumber);
    }

    @And("^User type text to Word file$")
    public void userTypeTextToWordFile() {
        wordEditorPage
                .sendTextToWordEditor();
    }

    @And("^User set name to Word file$")
    public void userSetNameToWordFile() {
        wordEditorPage
                .setWordFileName();
    }

    @Then("^The Word file must contains the same text$")
    public void checkThatOpenedWordFileContainsTheSameText() {
        softAssert.assertTrue(checkFolderName(), "Folder name wrong");
        softAssert.assertTrue(wordEditorPage.getTextFromWordEditor(),
                "Text, received from word editor, is not as expected");
        softAssert.assertAll();
    }
}
