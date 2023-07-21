package stepDefinition;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import testRunner.TestHooks;
import utilities.ExcelHelper;
import utilities.actionHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StepDefinition {

    public static WebDriver driver;
    Actions actions;
    ExtentSparkReporter htmlReport;
    ExtentReports extent;

    public static ExtentTest test;
    ExcelHelper excelData;

    public StepDefinition(/*WebDriver driver*/) {
//        this.driver = driver;
    }

    @Parameters({"browser","url"})
    @Before
    public void setUp(String browser, String url) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Public/TAWANA SISEKO/LumaDemoBDD/src/test/resources/chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();

        /*ExtentReports extentReports = new ExtentReports();
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
        htmlReport.config().setDocumentTitle("Demo shop Automation Report 3");
        htmlReport.config().setReportName("Test Report");
        htmlReport.config().setTheme(Theme.DARK);
        htmlReport.config().setTimeStampFormat("EEEE, MMM dd, yyyy, hh:mm a '('zzz')'");
*/
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
        if (driver != null) {
            driver.quit();
        }
    }


    @Given("I am on the home page")
    public void i_am_on_the_home_page() throws InterruptedException {
        test.info("Go to the website");
        driver.findElement(By.xpath("(//img)[1]")).click();
        Thread.sleep(3000);
    }
    @When("Scroll down, hover over the Radiant Tee and Click the add to compare link")
    public void scroll_down_hover_over_the_radiant_tee_and_click_the_add_to_compare_link() throws InterruptedException {
        test = extent.createTest("TC-001:","Verify if a user not logged in can add product to compare multiple products view");
        test.info("Hovering and Clicking Radiant Tee");
        actions = new Actions(driver);
        WebElement hover = driver.findElement(By.xpath("(//img[@alt='Radiant Tee'])[1]"));
        actions.moveToElement(hover).build().perform();
        driver.findElement(By.xpath("(//a[@title='Add to Compare'])[1]")).click();
        Thread.sleep(3000);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);

    }

    @Then("Click the comparison list link")
    public void click_the_comparison_list_link() throws InterruptedException {
        driver.findElement(By.xpath("(//a[normalize-space()='comparison list'])[1]")).click();
        Thread.sleep(3000);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
    }

    @When("Scroll down, Click on Breath-Easy Tank")
    public void scrollDownClickOnBreathEasyTank() throws InterruptedException {
        driver.findElement(By.xpath("(//a[normalize-space()='Breathe-Easy Tank'])[1]")).click();
        Thread.sleep(3000);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
    }

    @When("Scroll down, Click on Argus All-Weather Tank")
    public void scrollDownClickOnArgusAllWeatherTank() throws InterruptedException {
        driver.findElement(By.xpath("(//a[normalize-space()='Argus All-Weather Tank'])[1]")).click();
        Thread.sleep(3000);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
    }

    @And("Click the remove icon and I should see the reassurance question pop-up")
    public void clickTheRemoveIconAndIShouldSeeTheReassuranceQuestionPopUp() throws InterruptedException {
        driver.findElement(By.xpath("(//a[@title='Remove Product'])[1]")).click();
        Thread.sleep(3000);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
        WebElement assuranceMessage = driver.findElement(By.xpath("(//div[contains(text(),'Are you sure you want to remove this item from you')])[1]"));
        String expected = "Are you sure you want to remove this item from your Compare Products list?";
        String actual = assuranceMessage.getText();
        Assert.assertEquals(expected,actual);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);

    }

    @And("Click the OK button and I should see the confirmation of the removal")
    public void clickTheOKButtonAndIShouldSeeTheConfirmationOfTheRemoval() throws InterruptedException {
        driver.findElement(By.xpath("(//span[normalize-space()='OK'])[1]")).click();
        Thread.sleep(3000);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
        WebElement confimationMessage = driver.findElement(By.xpath("(//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)'])[1]"));
        String expected = "You removed product Argus All-Weather Tank from the comparison list.";
        String actual = confimationMessage.getText();
        Assert.assertEquals(expected,actual);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
    }

    @And("Click the search icon and I should see the message for the search")
    public void clickTheSearchIconAndIShouldSeeTheMessageForTheSearch() throws InterruptedException {
        driver.findElement(By.xpath("(//button[@title='Search'])[1]")).click();
        Thread.sleep(3000);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
        WebElement result = driver.findElement(By.xpath("//span[@class='base'])[1]"));
        String expected = "Search results for: 'Miko Pullover Hoodie'";
        String actual = result.getText();
        Assert.assertEquals(expected,actual);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
    }

    @When("Scroll down, hover over the Hero Hoodie, Select the product size,Select the product color")
    public void scrollDownHoverOverTheHeroHoodieSelectTheProductSizeSelectTheProductColor() throws InterruptedException {
        actions = new Actions(driver);
        WebElement hover = driver.findElement(By.xpath("(//img[@alt='Hero Hoodie'])[1]"));
        actions.moveToElement(hover).build().perform();
        Thread.sleep(3000);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
        driver.findElement(By.xpath("(//div[@id='option-label-size-143-item-168'])[4]")).click();
        Thread.sleep(3000);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
        driver.findElement(By.xpath("(//div[@id='option-label-color-93-item-53'])[1]")).click();
        Thread.sleep(3000);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
    }

    @Then("Click the Miko Pullover Hoodie, Select the product size, Select the product color")
    public void clickTheMikoPulloverHoodieSelectTheProductSizeSelectTheProductColor() throws InterruptedException {
        driver.findElement(By.xpath("(//a[normalize-space()='Miko Pullover Hoodie'])[1]")).click();
        Thread.sleep(3000);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
        driver.findElement(By.xpath("(//div[@id='option-label-size-143-item-167'])[1]")).click();
        Thread.sleep(3000);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
        driver.findElement(By.xpath("(//div[@id='option-label-color-93-item-56'])[1]")).click();
        Thread.sleep(3000);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
    }

    @Then("I should see the word Compare Products")
    public void iShouldSeeTheWordCompareProducts() throws InterruptedException {
        WebElement compareProduct = driver.findElement(By.xpath("//span[@class='base']"));
        String expected = "Compare Products";
        String actual = compareProduct.getText();
        Assert.assertEquals(expected,actual);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
    }

    @And("Add to compare the Breath-Easy Tank")
    public void addToCompareTheBreathEasyTank() throws InterruptedException {
        driver.findElement(By.xpath("(//span[contains(text(),'Add to Compare')])[1]")).click();
        Thread.sleep(3000);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
    }


    @And("I should see a success message for Breathe-Easy Tank")
    public void iShouldSeeASuccessMessageForBreatheEasyTank() {
        WebElement appearingMessage = driver.findElement(By.xpath("(//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)'])[1]"));
        String expected = "You added product Breathe-Easy Tank to the comparison list.";
        String actual = appearingMessage.getText();
        Assert.assertEquals(expected,actual);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
    }

    @And("Add to compare the Argus All-Weather Tank")
    public void addToCompareTheArgusAllWeatherTank() throws InterruptedException {
        driver.findElement(By.xpath("(//span[contains(text(),'Add to Compare')])[1]")).click();
        Thread.sleep(5000);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
    }

    @And("I should see a success message for Argus All-Weather Tank")
    public void iShouldSeeASuccessMessageForArgusAllWeatherTank() {
        WebElement appearingMessage = driver.findElement(By.xpath("(//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)'])[1]"));
        String expected = "You added product Argus All-Weather Tank to the comparison list.";
        String actual = appearingMessage.getText();
        Assert.assertEquals(expected,actual);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
    }

    @And("I should see a success message for Radiant Tee")
    public void iShouldSeeASuccessMessageForRadiantTee() {
        WebElement appearingMessage = driver.findElement(By.xpath("(//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)'])[1]"));
        String expected = "You added product Radiant Tee to the comparison list.";
        String actual = appearingMessage.getText();
        Assert.assertEquals(expected,actual);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
    }

    @And("Add to cart the Hero Hoodie")
    public void addToCartTheHeroHoodie() throws InterruptedException {
        driver.findElement(By.xpath("(//span[contains(text(),'Add to Cart')])[4]")).click();
        Thread.sleep(3000);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
    }

    @Then("I should see the message confirming that Hero Hoodie is added")
    public void iShouldSeeTheMessageConfirmingThatHeroHoodieIsAdded() {
        WebElement appearingMessage = driver.findElement(By.xpath("(//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)'])[1]"));
        String expected = "You added Hero Hoodie to your shopping cart.";
        String actual = appearingMessage.getText();
        Assert.assertEquals(expected,actual);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
    }

    @And("Click the shopping cart link and I should see the word Shipping Cart")
    public void clickTheShoppingCartLinkAndIShouldSeeTheWordShippingCart() throws InterruptedException {
        driver.findElement(By.xpath("(//a[normalize-space()='shopping cart'])[1]")).click();
        Thread.sleep(3000);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
        WebElement shoppingCart = driver.findElement(By.xpath("(//span[@class='base'])[1]"));
        String expected = "Shopping Cart";
        String actual = shoppingCart.getText();
        Assert.assertEquals(expected,actual);
        test.log(Status.INFO,"Taking a screenshot");
        actionHelper.talkScreenshot(driver);
    }

    @When("Navigate the search field and enter Miko Pullover Hoodie")
    public void navigateTheSearchFieldAndEnterMikoPulloverHoodie() throws InterruptedException {
        String productName = excelData.getCellData(1, 11);

        WebElement search = driver.findElement(By.xpath("(//input[@id='search'])[1]"));
        search.clear();
        driver.findElement(By.xpath("(//input[@id='search'])[1]")).sendKeys(productName);
        Thread.sleep(3000);
        actionHelper.talkScreenshot(driver);
    }

    @And("Add to cart the Miko Pullover Hoodie")
    public void addToCartTheMikoPulloverHoodie() throws InterruptedException {
        driver.findElement(By.xpath("(//span[normalize-space()='Add to Cart'])[1]")).click();
        Thread.sleep(3000);
        actionHelper.talkScreenshot(driver);
    }

    @And("I should see the message confirming that Miko Pullover Hoodie is added")
    public void iShouldSeeTheMessageConfirmingThatMikoPulloverHoodieIsAdded() {
        WebElement appearingMessage = driver.findElement(By.xpath("(//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)'])[1]"));
        String expected = "You added Miko Pullover Hoodie to your shipping cart.";
        String actual = appearingMessage.getText();
        Assert.assertEquals(expected,actual);
        actionHelper.talkScreenshot(driver);
    }

    @When("Navigate to the cart icon and click it")
    public void navigateToTheCartIconAndClickIt() throws InterruptedException {
        driver.findElement(By.xpath("(//a[@class='action showcart'])[1]")).click();
        Thread.sleep(3000);
        actionHelper.talkScreenshot(driver);
    }

    @And("Navigate to the proceed to checkout button and click it")
    public void navigateToTheProceedToCheckoutButtonAndClickIt() throws InterruptedException {
        driver.findElement(By.xpath("(//button[normalize-space()='Proceed to Checkout'])[1]")).click();
        Thread.sleep(3000);
        actionHelper.talkScreenshot(driver);
    }

    @And("I should see the word Order Summary")
    public void iShouldSeeTheWordOrderSummary() {
        WebElement appearingWords = driver.findElement(By.xpath("(//span[@class='title'])[1]"));
        String expected = "Order Summary";
        String actual = appearingWords.getText();
        Assert.assertEquals(expected,actual);
        actionHelper.talkScreenshot(driver);
    }

    @Then("Enter First name, Last name, Company, Street address, City, Province, Postal Code, Country, Phone number and Click the next button")
    public void enterFirstNameLastNameCompanyStreetAddressCityProvincePostalCodeCountryPhoneNumberAndClickTheNextButton() throws InterruptedException {
        String FirstName = excelData.getCellData(1, 2);
        String LastName = excelData.getCellData(1, 3);
        String Company = excelData.getCellData(1, 4);
        String Address = excelData.getCellData(1, 5);
        String City = excelData.getCellData(1, 6);
        String Province = excelData.getCellData(1, 7);
        String Code = excelData.getCellData(1, 8);
        String Phone = excelData.getCellData(1, 10);

        WebElement fName = driver.findElement(By.xpath("(//input[@id='GY2E0DB'])[1]"));
        fName.clear();
        driver.findElement(By.xpath("(//input[@id='GY2E0DB'])[1]")).sendKeys(FirstName);
        Thread.sleep(3000);actionHelper.talkScreenshot(driver);
        WebElement lName = driver.findElement(By.xpath("(//input[@id='Y2BYPH9'])[1]"));
        lName.clear();
        driver.findElement(By.xpath("(//input[@id='Y2BYPH9'])[1]")).sendKeys(LastName);
        Thread.sleep(3000);actionHelper.talkScreenshot(driver);
        WebElement company = driver.findElement(By.xpath("(//input[@id='FTU1PCW'])[1]"));
        company.clear();
        driver.findElement(By.xpath("(//input[@id='FTU1PCW'])[1]")).sendKeys(Company);
        Thread.sleep(3000);actionHelper.talkScreenshot(driver);
        WebElement street = driver.findElement(By.xpath("(//input[@id='PBE92LQ'])[1]"));
        street.clear();
        driver.findElement(By.xpath("(//input[@id='PBE92LQ'])[1]")).sendKeys(Address);
        Thread.sleep(3000);actionHelper.talkScreenshot(driver);
        WebElement city = driver.findElement(By.xpath("(//input[@id='V6N6V8W'])[1]"));
        city.clear();
        driver.findElement(By.xpath("(//input[@id='V6N6V8W'])[1]")).sendKeys(City);
        Thread.sleep(3000);actionHelper.talkScreenshot(driver);
        WebElement province = driver.findElement(By.xpath("(//input[@id='UAAGOKH'])[1]"));
        province.clear();
        driver.findElement(By.xpath("(//input[@id='UAAGOKH'])[1]")).sendKeys(Province);
        Thread.sleep(3000);actionHelper.talkScreenshot(driver);
        WebElement code = driver.findElement(By.xpath("(//input[@id='QJ4NN09'])[1]"));
        code.clear();
        driver.findElement(By.xpath("(//input[@id='QJ4NN09'])[1]")).sendKeys(Code);
        Thread.sleep(3000);actionHelper.talkScreenshot(driver);
        driver.findElement(By.xpath("(//select[@id='NMLFNGW'])[1]")).click();
        Thread.sleep(1500);actionHelper.talkScreenshot(driver);
        driver.findElement(By.xpath("//option[@value='ZA']")).click();
        Thread.sleep(3000);actionHelper.talkScreenshot(driver);
        WebElement phone = driver.findElement(By.xpath("(//input[@id='H28R7YU'])[1]"));
        phone.clear();
        driver.findElement(By.xpath("(//input[@id='H28R7YU'])[1]")).sendKeys(Phone);
        Thread.sleep(3000);actionHelper.talkScreenshot(driver);
        driver.findElement(By.xpath("(//span[normalize-space()='Next'])[1]")).click();
        Thread.sleep(3000);actionHelper.talkScreenshot(driver);
    }

    @And("I should see Ship To: and click the place order button")
    public void iShouldSeeShipToAndClickThePlaceOrderButton() throws InterruptedException {
        WebElement appearingWords = driver.findElement(By.xpath("(//span[normalize-space()='Ship To:'])[1]"));
        String expected = "Ship To:";
        String actual = appearingWords.getText();
        Assert.assertEquals(expected,actual);
        actionHelper.talkScreenshot(driver);
        driver.findElement(By.xpath("(//span[normalize-space()='Place Order'])[1]")).click();
        actionHelper.talkScreenshot(driver);
        Thread.sleep(3000);
    }

    @And("I should see Thank you for your purchase!")
    public void iShouldSeeThankYouForYourPurchase() {
        WebElement appearingWords = driver.findElement(By.xpath("(//span[@class='base'])[1]"));
        String expected = "Thank you for your purchase!";
        String actual = appearingWords.getText();
        Assert.assertEquals(expected,actual);
        actionHelper.talkScreenshot(driver);
    }

    @When("Scroll down, hover over the Men tab and Tops tab")
    public void scrollDownHoverOverTheMenTabAndTopsTab() throws InterruptedException {
        actions = new Actions(driver);
        WebElement men = driver.findElement(By.xpath("(//a[@id='ui-id-5'])[1]"));
        actions.moveToElement(men).perform();
        actionHelper.talkScreenshot(driver);
        Thread.sleep(3000);
        //Hover on top
        WebElement tops = driver.findElement(By.xpath("(//a[@id='ui-id-17'])[1]"));
        actions.moveToElement(tops).build().perform();
        actionHelper.talkScreenshot(driver);
        Thread.sleep(3000);
    }

    @And("Click the Tees tab and I should see the word Tees")
    public void clickTheTeesTabAndIShouldSeeTheWordTees() throws InterruptedException {
        driver.findElement(By.xpath("(//a[@id='ui-id-21'])[1]")).click();
        Thread.sleep(3000);actionHelper.talkScreenshot(driver);
        WebElement tees = driver.findElement(By.xpath("(//span[@class='base'])[1]"));
        String expected = "Tees";
        String actual = tees.getText();
        Assert.assertEquals(expected,actual);
        actionHelper.talkScreenshot(driver);
    }

    @And("Hover over the Logan HeatTec Tee")
    public void hoverOverTheLoganHeatTecTee() throws InterruptedException {
        actions = new Actions(driver);
        WebElement hover = driver.findElement(By.xpath("(//div[contains(@class,'price-box price-final_price')])[3]"));
        actions.moveToElement(hover).build().perform();
        actionHelper.talkScreenshot(driver);
        Thread.sleep(3000);
    }

    @And("Click the add to cart button")
    public void clickTheAddToCartButton() throws InterruptedException {
        driver.findElement(By.xpath("(//span[contains(text(),'Add to Cart')])[3]")).click();
        actionHelper.talkScreenshot(driver);
        Thread.sleep(3000);
    }

    @Then("I should see the error message")
    public void iShouldSeeTheErrorMessage() {
        WebElement error = driver.findElement(By.xpath("(//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)'])[1]"));
        String expected = "You need to choose options for your item.";
        String actual = error.getText();
        Assert.assertEquals(expected,actual);
        actionHelper.talkScreenshot(driver);
    }

    @When("Navigate and click sign in")
    public void navigateAndClickSignIn() {
        driver.findElement(By.xpath("(//a[contains(text(),'Sign In')])[1]")).click();
        actionHelper.talkScreenshot(driver);
    }

    @And("I should see Customer Login")
    public void iShouldSeeCustomerLogin() {
        WebElement login = driver.findElement(By.xpath("(//span[@class='base'])[1]"));
        String expected = "Customer Login";
        String actual = login.getText();
        Assert.assertEquals(expected,actual);
        actionHelper.talkScreenshot(driver);
    }

    @And("Enter Email, Password and Click Sign In")
    public void enterEmailPasswordAndClickSignIn() throws InterruptedException {
        String Email = excelData.getCellData(1, 0);
        String Password = excelData.getCellData(1, 1);

        WebElement EmailElement = driver.findElement(By.xpath("(//input[@id='email'])[1]"));
        EmailElement.clear();
        driver.findElement(By.xpath("(//input[@id='email'])[1]")).sendKeys(Email);
        Thread.sleep(2000);actionHelper.talkScreenshot(driver);
        WebElement password = driver.findElement(By.xpath("(//input[@id='pass'])[1]"));
        password.clear();
        driver.findElement(By.xpath("(//input[@id='pass'])[1]")).sendKeys(Password);
        Thread.sleep(2000);actionHelper.talkScreenshot(driver);
        driver.findElement(By.xpath("(//span[contains(text(),'Sign In')])[1]")).click();
        actionHelper.talkScreenshot(driver);
    }

    @Then("I should see the word Welcome")
    public void iShouldSeeTheWordWelcome() {
        WebElement welcomeMessage = driver.findElement(By.xpath("(//span[@class='logged-in'][normalize-space()='Welcome, Seko Msuthu!'])[1]"));
        String expected = "Welcome, Seko Msuthu!";
        String actual = welcomeMessage.getText();
        Assert.assertEquals(expected,actual);
        actionHelper.talkScreenshot(driver);
    }
}

