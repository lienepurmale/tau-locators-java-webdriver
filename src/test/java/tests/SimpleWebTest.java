package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class SimpleWebTest {

    private WebDriverWait wait;

    private WebDriver driver;


    @BeforeEach
    public void initWebDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\liene\\Desktop\\tau-locators-java-webdriver\\src\\main\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void searchDuckDuckGo() {
        // Load the page
        driver.get("https://www.duckduckgo.com");

        // Enter search phrase
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_form_input_homepage")));
        WebElement searchInput = driver.findElement(By.id("search_form_input_homepage"));
        searchInput.sendKeys("giant panda");

        // Click search button
        WebElement searchButton = driver.findElement(By.id("search_button_homepage"));
        searchButton.click();


        // Wait for results to appear
        wait.until(ExpectedConditions.titleContains("giant panda"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ikg2IXiCD14iVX7AdZo1")));


        // Make sure each result contains the word "panda"
        List<WebElement> resultLinks = driver.findElements(By.cssSelector("div.results_links_deep a.result__a"));
        for (WebElement link : resultLinks) {
            assertTrue(link.getText().matches("(?i).*panda.*"));
        }
    }

    @AfterEach
    public void quitWebDriver() {
        driver.quit();
    }
}
