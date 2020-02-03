package Nick_Maven.Framework.test;

import Nick_Maven.Framework.page.CloudGoogleHomePage;
import Nick_Maven.Framework.page.CloudGooglePlatformPricingCalculatorPage;
import Nick_Maven.Framework.page.TenMinuteMailHomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static Nick_Maven.Framework.service.ListEstimateCheckService.*;

public class Hardcore extends CommonConditions {

    public void sendParametrsFromPageToCheckService(CloudGooglePlatformPricingCalculatorPage cloudGooglePricingCalculatorTest){
        getConcatedTotalPriceFromPage(cloudGooglePricingCalculatorTest.listEstimatePriceField());
        getConcatedInstancesFromPage(cloudGooglePricingCalculatorTest.listEstimateInstanceField());
        getConcatedVmClassFromPage(cloudGooglePricingCalculatorTest.listEstimateVmClassField());
        getConcatedRegionFromPage(cloudGooglePricingCalculatorTest.listEstimateRegionField());
        getConcatedSSDFromPage(cloudGooglePricingCalculatorTest.listEstimateSSDField());
        getConcatedCommitmentTermFromPage(cloudGooglePricingCalculatorTest.listEstimateCommitmentTermField());
    }

    @Test(description = "Framework Hardcore task test")
    public void createEmailReceptionTotalAmountTest() {
        SoftAssert softAssert = new SoftAssert();

        CloudGooglePlatformPricingCalculatorPage cloudGooglePricingCalculatorTest = new CloudGoogleHomePage(driver)
                .openPage()
                .makeSearch(SEARCH_TEXT)
                .chooseSearchResult()
                .selectComputeEngineSection()
                .setNumberOfInstances()
                .selectOSAndSoftware()
                .selectMachineClass()
                .selectMachineType()
                .addGPUs()
                .selectLocalSSD()
                .selectDataCenterLocation()
                .selectCommittedUsage()
                .clickButtonAddToEstimate();

        addConcatedTotalPriceToTempList(cloudGooglePricingCalculatorTest.listEstimatePriceField());
        sendParametrsFromPageToCheckService(cloudGooglePricingCalculatorTest);

        cloudGooglePricingCalculatorTest
                .waitListEstimateField();

        softAssert.assertTrue(startListEstimateCheck());
        logger.debug("Check condition for ALL PARAMETRS from waitListEstimateField IS " + listEstimateCheckFlag) ;

        cloudGooglePricingCalculatorTest
                .clickButtonEmailEstimate();

        openNewTab().switchTab(1);

        TenMinuteMailHomePage tenMinuteMail = new TenMinuteMailHomePage(driver);
        String generatedEmail = tenMinuteMail
                .openPage()
                .copyGeneratedEmail();
        logger.debug("Check generated Email " + generatedEmail) ;
        switchTab(0);
        cloudGooglePricingCalculatorTest.insertEmailEstimate(generatedEmail)
                .sendEmail();
        switchTab(1);

        tenMinuteMail.checkReceivedEmail().addToListTotalAmountPrice(tempList);

        for (String s : tempList) {
            logger.info("Check received total price " + s);
        }

        softAssert.assertEquals(tempList.get(0), tempList.get(1));
        softAssert.assertAll();
    }
}