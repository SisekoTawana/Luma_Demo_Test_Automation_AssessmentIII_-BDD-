package testRunner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import utilities.ExcelHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestHooks {
    private WebDriver driver;
    ExtentSparkReporter htmlReport;
    ExtentReports extent;

    public static ExtentTest test;
    ExcelHelper excelData;
    @Parameters({"browser", "url"})
    @Before
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

            ExtentReports extentReports = new ExtentReports();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("LumaTestReport.html");
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Browser", browser);
            extentReports.setSystemInfo("URL", url);

            String fileSeparator = System.getProperty("file.separator");
            String file = System.getProperty("user.dir") + fileSeparator + "src" + fileSeparator + "test" + fileSeparator + "java" + fileSeparator + "reporting" + fileSeparator + "LumaTestReport_"
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy_HH.mm.ss")) + ".html";
            htmlReport = new ExtentSparkReporter(file);
            extent = new ExtentReports();
            extent.attachReporter(htmlReport);
            //ExtentReports - configuration items to change the look and feel
            htmlReport.config().setDocumentTitle("Demo shop Automation Report 2");
            htmlReport.config().setReportName("Test Report");
            htmlReport.config().setTheme(Theme.DARK);
            htmlReport.config().setTimeStampFormat("EEEE, MMM dd, yyyy, hh:mm a '('zzz')'");
        }catch (Exception e) {
            Assert.fail("something went wrong during setup " + e);
            throw new RuntimeException(e);
        }
        excelData = new ExcelHelper();
        excelData.setupExcel();
    }
    @AfterMethod
    public void testResults(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            if (test != null) {
                test.log(Status.PASS, result.getTestName());
            }
        } else if (result.getStatus() == ITestResult.FAILURE) {
            if (test != null) {
                test.log(Status.FAIL, result.getThrowable());
            }
        } else {
            if (test != null) {
                test.log(Status.SKIP, result.getTestName());
            }
        }

    }
    @After
    public void tearDown() {
        // Close the WebDriver after each scenario
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
