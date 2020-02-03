package Nick_Maven.Framework.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CloudGoogleSearchPage extends AbstractPage {
    private static final String JS_CLICK = "arguments[0].click();";
    CloudGoogleSearchPage(WebDriver driver) {
        super(driver);
    }

    public CloudGoogleSearchPage openPage() {
        return this;
    }

    @FindBy(xpath = "//*[contains(@class,'gsc-thumbnail-inside')]//b[text()='Google Cloud Platform Pricing Calculator']")
    private WebElement chooseSearchLink;


    public CloudGooglePlatformPricingCalculatorPage chooseSearchResult() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(chooseSearchLink));
        ((JavascriptExecutor) driver).executeScript(JS_CLICK, chooseSearchLink);

        logger.info("Search link opened");
        return new CloudGooglePlatformPricingCalculatorPage(driver);
    }
}