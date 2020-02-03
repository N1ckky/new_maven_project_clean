package Nick_Maven.Framework.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CloudGoogleHomePage extends AbstractPage {
    private static final String HOMEPAGE_URL = "https://cloud.google.com/";

    @FindBy(xpath = "//*[(contains(@class,'devsite-search-query'))]")
    private WebElement searchField;

    public CloudGoogleHomePage(WebDriver driver) {
        super(driver);
    }

    public CloudGoogleHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public CloudGoogleSearchPage makeSearch(String searchText) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(searchField));
        searchField.sendKeys(searchText,Keys.ENTER);
        logger.info("Success search");
        return new CloudGoogleSearchPage(driver);
    }
}