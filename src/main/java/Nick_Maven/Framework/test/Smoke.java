package Nick_Maven.Framework.test;

import Nick_Maven.Framework.page.CloudGoogleHomePage;
import Nick_Maven.Framework.page.CloudGoogleSearchPage;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Smoke extends CommonConditions {

    @Test(description = "Smoke test google cloud calculator")
    public void createEmailReceivationTotalAmountTest() {

        CloudGoogleSearchPage cloudGooglePricingCalculatorTest = new CloudGoogleHomePage(driver)
                .openPage()
                .makeSearch(SEARCH_TEXT);

        assertThat(cloudGooglePricingCalculatorTest, is(notNullValue()));
    }
}