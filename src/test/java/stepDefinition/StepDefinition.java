package stepDefinition;

import io.cucumber.java.en.And;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import testRunner.TestHooks;
import testRunner.TestRunner;


public class StepDefinition {

    public static WebDriver driver;
    Actions actions;

    public StepDefinition(/*WebDriver driver*/) {
//        this.driver = driver;
    }

    @Given("I am on the home page")
    public void i_am_on_the_home_page() {
//        WebDriver driver = TestHooks.driver;
        System.setProperty("webdriver.chrome.driver", "C:/Users/Public/TAWANA SISEKO/LumaDemoBDD/src/test/resources/chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("(//img)[1]"));
    }
    @When("Scroll down, hover over the Radiant Tee and Click the add to compare link")
    public void scroll_down_hover_over_the_radiant_tee_and_click_the_add_to_compare_link() {
        actions = new Actions(driver);
        WebElement hover = driver.findElement(By.xpath("(//img[@alt='Radiant Tee'])[1]"));
        actions.moveToElement(hover).build().perform();
        driver.findElement(By.xpath("(//a[@title='Add to Compare'])[1]")).click();
    }

    @Then("Click the comparison list link")
    public void click_the_comparison_list_link() {
        driver.findElement(By.xpath("(//a[normalize-space()='comparison list'])[1]")).click();
    }

    @When("Scroll down, Click on Breath-Easy Tank")
    public void scrollDownClickOnBreathEasyTank() {
        driver.findElement(By.xpath("(//a[normalize-space()='Breathe-Easy Tank'])[1]")).click();
    }

    @When("Scroll down, Click on Argus All-Weather Tank")
    public void scrollDownClickOnArgusAllWeatherTank() {
        driver.findElement(By.xpath("(//a[normalize-space()='Argus All-Weather Tank'])[1]")).click();
    }

    @And("Click the remove icon and I should see the reassurance question pop-up")
    public void clickTheRemoveIconAndIShouldSeeTheReassuranceQuestionPopUp() {
        driver.findElement(By.xpath("(//a[@title='Remove Product'])[1]")).click();
        WebElement assuranceMessage = driver.findElement(By.xpath("(//div[contains(text(),'Are you sure you want to remove this item from you')])[1]"));
        String expected = "Are you sure you want to remove this item from your Compare Products list?";
        String actual = assuranceMessage.getText();
        Assert.assertEquals(expected,actual);

    }

    @And("Click the OK button and I should see the confirmation of the removal")
    public void clickTheOKButtonAndIShouldSeeTheConfirmationOfTheRemoval() {
        driver.findElement(By.xpath("(//span[normalize-space()='OK'])[1]")).click();
        WebElement confimationMessage = driver.findElement(By.xpath("(//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)'])[1]"));
        String expected = "You removed product Argus All-Weather Tank from the comparison list.";
        String actual = confimationMessage.getText();
        Assert.assertEquals(expected,actual);
    }

    @And("Click the search icon and I should see the message for the search")
    public void clickTheSearchIconAndIShouldSeeTheMessageForTheSearch() {
        driver.findElement(By.xpath("(//button[@title='Search'])[1]")).click();
        WebElement result = driver.findElement(By.xpath("//span[@class='base'])[1]"));
        String expected = "Search results for: 'Miko Pullover Hoodie'";
        String actual = result.getText();
        Assert.assertEquals(expected,actual);
    }

    @When("Scroll down, hover over the Hero Hoodie, Select the product size,Select the product color")
    public void scrollDownHoverOverTheHeroHoodieSelectTheProductSizeSelectTheProductColor() {
        actions = new Actions(driver);
        WebElement hover = driver.findElement(By.xpath("(//img[@alt='Hero Hoodie'])[1]"));
        actions.moveToElement(hover).build().perform();
        driver.findElement(By.xpath("(//div[@id='option-label-size-143-item-168'])[4]")).click();
        driver.findElement(By.xpath("(//div[@id='option-label-color-93-item-53'])[1]")).click();
    }

    @Then("Click the Miko Pullover Hoodie, Select the product size, Select the product color")
    public void clickTheMikoPulloverHoodieSelectTheProductSizeSelectTheProductColor() {
        driver.findElement(By.xpath("(//a[normalize-space()='Miko Pullover Hoodie'])[1]")).click();
        driver.findElement(By.xpath("(//div[@id='option-label-size-143-item-167'])[1]")).click();
        driver.findElement(By.xpath("(//div[@id='option-label-color-93-item-56'])[1]")).click();
    }

    @Then("I should see the word Compare Products")
    public void iShouldSeeTheWordCompareProducts() {
        WebElement compareProduct = driver.findElement(By.xpath("//span[@class='base']"));
        String expected = "Compare Products";
        String actual = compareProduct.getText();
        Assert.assertEquals(expected,actual);
    }

    @And("Add to compare the Breath-Easy Tank")
    public void addToCompareTheBreathEasyTank() {
        driver.findElement(By.xpath("(//a[normalize-space()='Breathe-Easy Tank'])[1]")).click();
    }

    @And("I should see a success message for Breathe-Easy Tank")
    public void iShouldSeeASuccessMessageForBreatheEasyTank() {
        WebElement appearingMessage = driver.findElement(By.xpath("(//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)'])[1]"));
        String expected = "You added product Breathe-Easy Tank to the comparison list.";
        String actual = appearingMessage.getText();
        Assert.assertEquals(expected,actual);
    }

    @And("Add to compare the Argus All-Weather Tank")
    public void addToCompareTheArgusAllWeatherTank() {
        driver.findElement(By.xpath("(//span[contains(text(),'Add to Compare')])[1]"));
    }

    @And("I should see a success message for Argus All-Weather Tank")
    public void iShouldSeeASuccessMessageForArgusAllWeatherTank() {
        WebElement appearingMessage = driver.findElement(By.xpath("(//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)'])[1]"));
        String expected = "You added product Argus All-Weather Tank to the comparison list.";
        String actual = appearingMessage.getText();
        Assert.assertEquals(expected,actual);
    }

    @And("I should see a success message for Radiant Tee")
    public void iShouldSeeASuccessMessageForRadiantTee() {
        WebElement appearingMessage = driver.findElement(By.xpath("(//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)'])[1]"));
        String expected = "You added product Radiant Tee to the comparison list.";
        String actual = appearingMessage.getText();
        Assert.assertEquals(expected,actual);
    }

    @And("Add to cart the Hero Hoodie")
    public void addToCartTheHeroHoodie() {
        driver.findElement(By.xpath("(//span[contains(text(),'Add to Cart')])[4]")).click();
    }

    @Then("I should see the message confirming that Hero Hoodie is added")
    public void iShouldSeeTheMessageConfirmingThatHeroHoodieIsAdded() {
        WebElement appearingMessage = driver.findElement(By.xpath("(//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)'])[1]"));
        String expected = "You added Hero Hoodie to your shipping cart.";
        String actual = appearingMessage.getText();
        Assert.assertEquals(expected,actual);
    }

    @And("Click the shopping cart link and I should see the word Shipping Cart")
    public void clickTheShoppingCartLinkAndIShouldSeeTheWordShippingCart() {
        driver.findElement(By.xpath("(//a[normalize-space()='shopping cart'])[1]")).click();
        WebElement shoppingCart = driver.findElement(By.xpath("(//span[@class='base'])[1]"));
        String expected = "Shopping Cart.";
        String actual = shoppingCart.getText();
        Assert.assertEquals(expected,actual);
    }

    @When("Navigate the search field and enter Miko Pullover Hoodie")
    public void navigateTheSearchFieldAndEnterMikoPulloverHoodie() {
        WebElement search = driver.findElement(By.xpath("(//input[@id='search'])[1]"));
        search.clear();
        driver.findElement(By.xpath("(//input[@id='search'])[1]")).sendKeys();
    }

    @And("Add to cart the Miko Pullover Hoodie")
    public void addToCartTheMikoPulloverHoodie() {
        driver.findElement(By.xpath("(//span[normalize-space()='Add to Cart'])[1]")).click();
    }

    @And("I should see the message confirming that Miko Pullover Hoodie is added")
    public void iShouldSeeTheMessageConfirmingThatMikoPulloverHoodieIsAdded() {
        WebElement appearingMessage = driver.findElement(By.xpath("(//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)'])[1]"));
        String expected = "You added Miko Pullover Hoodie to your shipping cart.";
        String actual = appearingMessage.getText();
        Assert.assertEquals(expected,actual);
    }

    @When("Navigate to the cart icon and click it")
    public void navigateToTheCartIconAndClickIt() {

    }
}
