package Nick_Maven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;



public class HelloWebDriver {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver","C:\\_webdriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.seleniumhq.org/");
        WebElement searchInput = driver.findElement(By.id("q"));
        searchInput.sendKeys("selenium java");
        WebElement searchBtn = driver.findElement(By.xpath("//*[@id='submit']"));
        searchBtn.click();
        Thread.sleep(2000);
        driver.quit();

    }
}
