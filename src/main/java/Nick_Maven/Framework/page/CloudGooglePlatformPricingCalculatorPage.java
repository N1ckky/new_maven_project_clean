package Nick_Maven.Framework.page;

import Nick_Maven.Framework.CustomConditions;
import Nick_Maven.Framework.service.TestDataReaderService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CloudGooglePlatformPricingCalculatorPage extends AbstractPage {
    private static WebElement IFRAME;
    private static final String TAG_IFRAME = "iframe";
    private static final String NUMBER_OF_INSTANCES = "testdata.numberOfInstances";

    public CloudGooglePlatformPricingCalculatorPage openPage() {
        return this;
    }

    @FindBy(xpath = "//*[contains(@class,'presets-buttons')]")
    private List<WebElement> computeEngineSection;

    @FindBy(xpath = "//input[@name='quantity' and @ng-model='listingCtrl.computeServer.quantity']")
    private WebElement numberOfInstancesField;

    @FindBy(xpath = "//*[@class='md-select-value' and @id='select_value_label_46']")
    private WebElement chooseOSAndSoftwareField;

    @FindBy(xpath = "//div[@id='select_container_66']//child::md-option")
    private List<WebElement> listOfOSAndSoftware;

    @FindBy(xpath = "//*[@class='md-select-value' and @id='select_value_label_47']")
    private WebElement chooseMachineClassField;

    @FindBy(xpath = "//div[@id='select_container_70']//child::md-option")
    private List<WebElement> listOfMachineClass;

    @FindBy(xpath = "//*[@class='md-select-value' and @id='select_value_label_50']")
    private WebElement chooseMachineTypeField;

    @FindBy(xpath = "//md-optgroup[@label='standard']//child::md-option")
    private List<WebElement> listOfMachineType;

    @FindBy(xpath = "//*[@class='md-container md-ink-ripple']")
    private List<WebElement> checkBoxesComputeEngineSection;

    @FindBy(xpath = "//*[@class='md-select-value' and @id='select_value_label_353']")
    private WebElement selectNumberOfGPUsField;

    @FindBy(xpath = "//div[@id='select_container_356']//child::md-option")
    private List<WebElement> listNumbeOfGPUs;

    @FindBy(xpath = "//*[@class='md-select-value' and @id='select_value_label_354']")
    private WebElement selectGPUTypeField;

    @FindBy(xpath = "//div[@id='select_container_358']//child::md-option")
    private List<WebElement> listGPUType;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_171']")
    private WebElement selectLocalSSDField;

    @FindBy(xpath = "//div[@id='select_container_173']//child::md-option")
    private List<WebElement> listOfLocalSSD;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_51']//child::div[@class='md-text ng-binding']")
    private WebElement selectDatacenterlocationField;

    @FindBy(xpath = "//div[@id='select_container_81']//child::md-option")
    private List<WebElement> listOfDatacenterlocation;

    @FindBy(xpath = "//*[@class='md-select-value' and @id='select_value_label_52']")
    private WebElement selectCommitedUsageField;

    @FindBy(xpath = "//div[@id='select_container_88']//child::md-option")
    private List<WebElement> listOfCommitedUsage;

    @FindBy(xpath = "//button[@aria-label='Add to Estimate']")
    private WebElement buttonAddToEstimate;

    @FindBy(xpath = "//md-card-content[@id='resultBlock']//child::md-list-item")
    private List<WebElement> listOfEstimate;

    @FindBy(xpath = "//button[@aria-label= 'Email Estimate']")
    private WebElement buttonEmailEstimate;

    @FindBy(xpath = "//button[@id= 'email_quote']")
    private WebElement emailInsertLable;

    @FindBy(xpath = "//*[contains(@ng-model,'.email')]")
    private WebElement emailInsertField;

    @FindBy(xpath = "//button[@type= 'button' and @aria-label='Send Email']")
    private WebElement buttonSendEmail;


    CloudGooglePlatformPricingCalculatorPage(WebDriver driver) {
        super(driver);
        iframeSwitch();
    }

    private void iframeSwitch() {
        while (driver.findElements(By.tagName(TAG_IFRAME)).size()!=0){
            new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(CustomConditions.jsReadyStateCompleted());
            logger.debug("amount of iframes " + driver.findElements(By.tagName(TAG_IFRAME)).size());
            IFRAME = driver.findElement(By.tagName(TAG_IFRAME));
            driver.switchTo().frame(IFRAME);
            new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(CustomConditions.jsReadyStateCompleted());
        }
    }

    public CloudGooglePlatformPricingCalculatorPage selectComputeEngineSection() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(CustomConditions.jsReadyStateCompleted());
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(computeEngineSection.get(0)));
        logger.debug("select compute engine section");
        computeEngineSection.get(0).click();
        return this;
    }

    public CloudGooglePlatformPricingCalculatorPage setNumberOfInstances() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(numberOfInstancesField));
        numberOfInstancesField.click();
        numberOfInstancesField.sendKeys(TestDataReaderService.getTestData(NUMBER_OF_INSTANCES));
        return this;
    }

    public CloudGooglePlatformPricingCalculatorPage selectOSAndSoftware() {
        jsExecutor(chooseOSAndSoftwareField);
        listOfOSAndSoftware.get(0).click();
        return this;
    }

    public CloudGooglePlatformPricingCalculatorPage selectMachineClass() {
        jsExecutor(chooseMachineClassField);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(listOfMachineClass.get(0)));
        jsExecutor(listOfMachineClass.get(0));
        return this;
    }

    public CloudGooglePlatformPricingCalculatorPage selectMachineType() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(chooseMachineTypeField));
        jsExecutor(chooseMachineTypeField);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(listOfMachineType.get(3)));
        jsExecutor(listOfMachineType.get(3));
        return this;
    }

    public CloudGooglePlatformPricingCalculatorPage addGPUs() {
        jsExecutor(checkBoxesComputeEngineSection.get(0));
        jsExecutor(selectNumberOfGPUsField);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(listNumbeOfGPUs.get(1)));
        jsExecutor(listNumbeOfGPUs.get(1));
        jsExecutor(selectGPUTypeField);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(listGPUType.get(3)));
        jsExecutor(listGPUType.get(3));
        return this;
    }

    public CloudGooglePlatformPricingCalculatorPage selectLocalSSD() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOf(selectLocalSSDField));
        jsExecutor(selectLocalSSDField);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(listOfLocalSSD.get(2)));
        jsExecutor(listOfLocalSSD.get(2));
        return this;
    }

    public CloudGooglePlatformPricingCalculatorPage selectDataCenterLocation() {
        jsExecutor(selectDatacenterlocationField);
        jsExecutor(listOfDatacenterlocation.get(7));
        return this;
    }

    public CloudGooglePlatformPricingCalculatorPage selectCommittedUsage() {
        jsExecutor(selectCommitedUsageField);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(listOfCommitedUsage.get(1)));
        jsExecutor(listOfCommitedUsage.get(1));
        return this;
    }

    public CloudGooglePlatformPricingCalculatorPage clickButtonAddToEstimate() {
        jsExecutor(buttonAddToEstimate);
        return this;
    }

    public String listEstimatePriceField() {
        return listOfEstimate.get(6).getText();
    }

    public String listEstimateInstanceField() {
        return listOfEstimate.get(2).getText();
    }

    public String listEstimateVmClassField() {
        return listOfEstimate.get(1).getText();
    }

    public String listEstimateRegionField() {
        return listOfEstimate.get(3).getText();
    }

    public String listEstimateSSDField() {
        return listOfEstimate.get(4).getText();
    }

    public String listEstimateCommitmentTermField() {
        return listOfEstimate.get(5).getText();
    }

    public CloudGooglePlatformPricingCalculatorPage waitListEstimateField() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOfAllElements(listOfEstimate));
        return this;
    }

    public CloudGooglePlatformPricingCalculatorPage clickButtonEmailEstimate() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(buttonEmailEstimate));
        buttonEmailEstimate.click();
        return this;
    }

    public CloudGooglePlatformPricingCalculatorPage insertEmailEstimate(String generatedEmail) {
        iframeSwitch();
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(emailInsertLable));
        jsExecutor(emailInsertLable);
        emailInsertField.sendKeys(generatedEmail);
        return this;
    }

    public CloudGooglePlatformPricingCalculatorPage sendEmail() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(buttonSendEmail));
        if (buttonSendEmail.isEnabled()) {
            jsExecutor(buttonSendEmail);
        } else {
            logger.error("Button 'SEND EMAIL' is not available");
        }
        return this;
    }
}