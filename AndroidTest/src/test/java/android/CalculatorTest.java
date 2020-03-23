package android;

import android.calculator.logger.Log;
import android.calculator.pages.CalculatorPage;
import org.decimal4j.util.DoubleRounder;
import org.testng.Assert;
import org.testng.annotations.Test;

import static android.calculator.Configs.*;

public class CalculatorTest extends CommonConditions {

    public CalculatorTest() {
        capabilities.setCapability("appActivity", "Calculator");
        capabilities.setCapability("appPackage", "com.android.calculator2");
    }

    @Test
    public void calculatorAdditionTest() {
        CalculatorPage calculatorPage = new CalculatorPage(driver);
        calculatorPage.getSumNumbers(one, two, three);
        Log.info("Result for PLUS operation: " + calculatorPage.getResult());
        Assert.assertEquals(calculatorPage.getResult(), "6");
        calculatorPage.clickButtonClear();
    }

    @Test
    public void calculatorDivisionTest() {
        CalculatorPage calculatorPage = new CalculatorPage(driver);
        calculatorPage.getDivisionNumbers(one, two, three);
        Log.info("Result for DIVISION operation: " + calculatorPage.getResult());
        Assert.assertEquals("" + DoubleRounder.round(Double.valueOf(calculatorPage.getResult()), 4), "0.1667");
        calculatorPage.clickButtonClear();
    }

    @Test
    public void calculatorMultiplyTest() {
        CalculatorPage calculatorPage = new CalculatorPage(driver);
        calculatorPage.geеMultiplicationNumbers(one, two, three);
        Log.info("Result for MULTIPLY operation: " + calculatorPage.getResult());
        Assert.assertEquals(calculatorPage.getResult(), "6");
        calculatorPage.clickButtonClear();
    }

    @Test
    public void calculatorSubtractionTest() {
        CalculatorPage calculatorPage = new CalculatorPage(driver);
        calculatorPage.getSubtractionNumbers(one, two, three);
        Log.info("Result for SUBSTRACTION operation: " + calculatorPage.getResult());
        Assert.assertEquals(calculatorPage.getResult(), "−4");
        calculatorPage.clickButtonClear();
    }
}
