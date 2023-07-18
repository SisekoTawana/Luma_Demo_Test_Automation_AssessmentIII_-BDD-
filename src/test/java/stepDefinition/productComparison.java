package stepDefinition;

import io.cucumber.java.en.And;
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




public class productComparison {

    WebDriver driver;
    Actions actions;

    @Given("I am on the home page")
    public void i_am_on_the_home_page() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
    }
    @When("Scroll down, hover over the Radiant Tee and Click the add to compare link")
    public void scroll_down_hover_over_the_radiant_tee_and_click_the_add_to_compare_link() {
        actions = new Actions(driver);
        WebElement hover = driver.findElement(By.xpath("(//img[@alt='Radiant Tee'])[1]"));
        actions.moveToElement(hover).build().perform();
        driver.findElement(By.xpath("(//a[@title='Add to Compare'])[1]")).click();
    }

    @And("I should see a success message")
    public void iShouldSeeASuccessMessage() {
        WebElement addedMessage = driver.findElement(By.xpath("(//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)'])[1]"));
        String expected = "You added product Radiant Tee to the ";
        String actual = addedMessage.getText();
        Assert.assertEquals(expected,actual);
    }
    @Then("Click the comparison list link")
    public void click_the_comparison_list_link() {
        driver.findElement(By.xpath("(//a[normalize-space()='comparison list'])[1]")).click();
    }

    @Then("I should see the Radiant Tee to the product compare page")
    public void iShouldSeeTheRadiantTeeToTheProductComparePage() {
        WebElement printThisPageLink = driver.findElement(By.xpath("(//span[normalize-space()='Print This Page'])[1]"));
        String expected = "Print This Page";
        String actual = printThisPageLink.getText();
        Assert.assertEquals(expected,actual);
    }

    @When("Scroll down, Click on Breath-Easy Tank")
    public void scrollDownClickOnBreathEasyTank() {
        
    }

    @And("Click the add to compare link")
    public void clickTheAddToCompareLink() {
        
    }

    @Then("I should see the Breath-Easy Tank to the product compare page")
    public void iShouldSeeTheBreathEasyTankToTheProductComparePage() {
        
    }

    @When("Scroll down, Click on Argus All-Weather Tank")
    public void scrollDownClickOnArgusAllWeatherTank() {
        
    }

    @Then("I should see the Argus All-Weather Tank to the product compare page")
    public void iShouldSeeTheArgusAllWeatherTankToTheProductComparePage() {
    }
}
