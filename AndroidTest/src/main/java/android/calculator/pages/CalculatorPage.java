package android.calculator.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalculatorPage {
    @FindBy(id = "op_add")
    private WebElement plusButton;

    @FindBy(id = "op_div")
    private WebElement divideButton;

    @FindBy(id = "op_mul")
    private WebElement multiplyButton;

    @FindBy(id = "op_sub")
    private WebElement minusButton;

    @FindBy(id = "eq")
    private WebElement equallyButton;

    @FindBy(id = "clr")
    private WebElement clearButton;

    @FindBy(id = "result")
    private WebElement resultField;

    WebDriver driver;

    public CalculatorPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public CalculatorPage clickNumberButton(int i) {
        driver.findElement(By.id(String.format("digit_%s", i))).click();
        return this;
    }

    public CalculatorPage getSumNumbers(int... numbersVarArgs) {
        for (int i = 0; i < numbersVarArgs.length; i++) {
            driver.findElement(By.id(String.format("digit_%s", numbersVarArgs[i]))).click();
            if (i<numbersVarArgs.length-1) clickButtonPlus();
        }
        clickButtonEquals();
        return this;
    }

    public CalculatorPage getDivisionNumbers(int... numbersVarArgs) {
        for (int i = 0; i < numbersVarArgs.length; i++) {
            driver.findElement(By.id(String.format("digit_%s", numbersVarArgs[i]))).click();
            if (i<numbersVarArgs.length-1) clickButtonDivide();
        }
        clickButtonEquals();
        return this;
    }

    public CalculatorPage getSubtractionNumbers(int... numbersVarArgs) {
        for (int i = 0; i < numbersVarArgs.length; i++) {
            driver.findElement(By.id(String.format("digit_%s", numbersVarArgs[i]))).click();
            if (i<numbersVarArgs.length-1) clickButtonMinus();
        }
        clickButtonEquals();
        return this;
    }

    public CalculatorPage geеMultiplicationNumbers(int... numbersVarArgs) {
        for (int i = 0; i < numbersVarArgs.length; i++) {
            driver.findElement(By.id(String.format("digit_%s", numbersVarArgs[i]))).click();
            if (i<numbersVarArgs.length-1) clickButtonMultiply();
        }
        clickButtonEquals();
        return this;
    }

    public CalculatorPage clickButtonPlus() {
        plusButton.click();
        return this;
    }

    public CalculatorPage clickButtonDivide() {
        divideButton.click();
        return this;
    }

    public CalculatorPage clickButtonMinus() {
        minusButton.click();
        return this;
    }

    public CalculatorPage clickButtonMultiply() {
        multiplyButton.click();
        return this;
    }

    public CalculatorPage clickButtonEquals() {
        equallyButton.click();
        return this;
    }

    public CalculatorPage clickButtonClear() {
        clearButton.click();
        return this;
    }

    public String getResult() {
        return resultField.getText();
    }
}
