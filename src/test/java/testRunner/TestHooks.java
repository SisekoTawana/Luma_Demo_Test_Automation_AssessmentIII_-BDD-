package testRunner;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class TestHooks {
    public static WebDriver driver;
    @Parameters({"browser", "url"})
    @BeforeTest
    //setting up WebDriver & extentReports
    public void setUp(String browser, String url) {
        try {

            if (browser.equalsIgnoreCase("Chrome")) {
                System.setProperty("webdriver.chrome.driver", "C:/Users/Public/TAWANA SISEKO/LumaDemoBDD/src/test/resources/chromedriver.exe");
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                driver.get("https://magento.softwaretestingboard.com/");
            } else if (browser.equalsIgnoreCase("Edge")) {
                System.setProperty("webdriver.edge.driver", "C:/Users/Public/TAWANA SISEKO/LumaDemoBDD/src/test/resources/msedgedriver.exe");
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new EdgeDriver(options);
                driver.get("https://magento.softwaretestingboard.com/");
            } else {
                throw new RuntimeException("Browser name '" + browser + "'could not be found. Check spelling");
            }

            driver.manage().window().maximize();
        }catch (Exception e) {
            Assert.fail("something went wrong during setup " + e);
            throw new RuntimeException(e);
        }

    }
    @After
    public void teardown() {
        // Close the WebDriver after each scenario
        if (driver != null) {
            driver.quit();
        }
    }
}
